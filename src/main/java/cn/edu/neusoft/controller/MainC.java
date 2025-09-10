package cn.edu.neusoft.controller;

import cn.edu.neusoft.dao.SpotDao;
import cn.edu.neusoft.model.Announcement;
import cn.edu.neusoft.model.Spot;
import cn.edu.neusoft.model.User;
import cn.edu.neusoft.statemachine.auth.AuthEvents;
import cn.edu.neusoft.statemachine.auth.AuthStateMachine;
import cn.edu.neusoft.statemachine.auth.AuthStates;
import cn.edu.neusoft.view.*;

import java.util.List;
import java.util.Scanner;

public class MainC {
	/**
	 * 认证状态机的实例。
	 */
	private final AuthStateMachine asm;
    
    /**
     * 存储已登录用户的信息。在全局范围内可用。
     */
    private User loggedInUser;

    public MainC() {
        this.asm = new AuthStateMachine();
        loggedInUser = null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * 对外操作的真正入口。
     */
    public void startApp() {
    	boolean isRunning = true;

    	while (isRunning) {
    		AuthStates authStates;
    		UserAuthC uac = new UserAuthC();
			switch (asm.getCurrentState()) {
			case NOT_AUTHENTICATED:
				int selection = UserAuthView.chooseLoginMethod(); //TODO: 可以用枚举来代替魔法常量，尽管魔法常量比魔法数字更好。
				final int exit = 0;
				final int inLogin = 1;
				final int inRegister = 2;
				
				switch (selection) {
				case inLogin:
					loggedInUser = uac.login();
					if (loggedInUser != null && loggedInUser.getUserId() != null) {
						asm.toggle(AuthEvents.ATTEMPT_LOGIN);
						userSession();
					}
					break;

				case inRegister:
					uac.register();
					break;
					
				case exit:
					isRunning = false;
					break;
					
				default:
					loggedInUser = uac.login();
					if (loggedInUser != null && loggedInUser.getUserId() != null) {
						asm.toggle(AuthEvents.ATTEMPT_LOGIN);
						userSession();
					}
					break;
				}
				break;

			default:
				break;
			}
		}
        System.out.println("感谢使用，程序已退出。");
        System.exit(0);
    }

    private void userSession() {
    	int admin = 0;
    	int generalUser = 1;
        if (loggedInUser != null && loggedInUser.getRole() == generalUser) {
            IndexView.indexOfGeneralUser();
            Scanner sc = new Scanner(System.in);
            UserCenterC userCenter = new UserCenterC();

            System.out.print("请选择要进行的操作: ");
            int manu = sc.nextInt();
            sc.nextLine();
            
          //TODO: 导航业务必须单独拎出来，成为一个新的状态机。
            
            switch (manu) { // 这里只负责处理状态。
                case 0: // 退出系统。
                    System.out.println("您已退出登录。系统将回到登录界面。");
                    loggedInUser = null; // 退出登录后，清空已登录用户的信息。
                    asm.toggle(AuthEvents.REQUEST_LOGOUT);
                    break;
                case 1: // 个人信息管理
                    userCenter.managerCenter(loggedInUser);
                    break;
                case 2: // 学习打卡
                    SpotLearnC slc = new SpotLearnC();
                    SpotDao sd = new SpotDao();
                    List<Spot> allSpots = sd.searchAllSpots(-1, 1); // 让程序启动时，默认显示所有信息。
                    int menu = SpotLearnView.showSpotsView(allSpots);
                    allSpots.clear();
                    slc.showSpots(menu, loggedInUser.getUserId());
                    sd = null;
                    break;
                case 3: // 管理学习记录
                    ManagerRecordC mr = new ManagerRecordC();
                    mr.manageRecord(getLoggedInUser());
                    break;
                case 4: // 查看热门景点排行
                    SpotDao spd = new SpotDao();
                    SpotLearnView.showPopularSpot(spd.getPopularSpots());
                    break;
                case 5: // 查看公告
                    ManageAnnouncementC manageAnnouncement = new ManageAnnouncementC();
                    manageAnnouncement.searchAnnouncement();
                    break;
            }
        } else if (loggedInUser != null && loggedInUser.getRole() == admin) {
            IndexView.indexOfAdmin();
            Scanner sc = new Scanner(System.in);
            int menu = sc.nextInt();
            sc.nextLine();
            ManagerUserC managerUser = new ManagerUserC();
            ManageSpotC manageSpot = new ManageSpotC();
            int operator;
            
          //TODO: 导航业务必须单独拎出来，成为一个新的状态机。
            
            switch (menu) {
                case 0:
                    System.out.println("您已退出登录。系统将回到登录界面。");
                    loggedInUser = null;
                    asm.toggle(AuthEvents.REQUEST_LOGOUT);
                    break;
                case 1: // 管理员的用户管理功能，包括增加、删除和查找。关于用户的修改，只能登录到对应的账户上进行。
                    operator = ManageUserView.mainView();
                    switch (operator) {
                        case 0:
                            loggedInUser = null;
                            asm.toggle(AuthEvents.REQUEST_LOGOUT);
                            break;
                        case 1:
                            managerUser.searchUsersC();
                            break;
                        case 2:
                            managerUser.deleteUserC(ManageUserView.deleteUserView());
                            break;
                        case 3:
                            managerUser.addUser(ManageUserView.addUserView());
                            break;
                    }
                    break;
                case 2: // 管理景点
                    operator = ManageSpotView.mainView();
                    switch (operator) {
                        case 0:
                            loggedInUser = null;
                            asm.toggle(AuthEvents.REQUEST_LOGOUT);
                            break;
                        case 1: // 添加景点
                            if (manageSpot.addSpot()) {
                                System.out.println("景点添加成功! ");
                            } else {
                                System.out.println("景点添加失败! ");
                            }
                            break;
                        case 2: // 删除景点
                            if (manageSpot.deleteSpot()) {
                                System.out.println("景点删除成功! ");
                            } else {
                                System.out.println("景点删除失败! ");
                            }
                            break;
                        case 3: // 查询景点
                            manageSpot.searchSpotsC();
                            break;
                    }
                    break;
                case 3: // 管理公告
                    operator = AnnouncementManageView.adminManagePage();
                    ManageAnnouncementC manageAnnouncement = new ManageAnnouncementC();
                    switch (operator) {
                        case 0:
                            loggedInUser = null;
                            asm.toggle(AuthEvents.REQUEST_LOGOUT);
                            break;
                        case 1: // 添加公告
                            Announcement announce = AnnouncementManageView.addAnnouncement();
                            manageAnnouncement.addAnnouncement(announce);
                            break;
                        case 2: // 删除公告
                            manageAnnouncement.deleteAnnouncement();
                            break;
                        case 3: // 修改公告
                            manageAnnouncement.updateAnnouncement();
                            break;
                        case 4: // 查询公告
                            manageAnnouncement.searchAnnouncement();
                            break;
                    }
                    break;
                case 4: // 查看学习记录
                    ManagerRecordC mr = new ManagerRecordC();
                    mr.manageRecord(getLoggedInUser());
                    break;
                case 5: // 查看热门景点排行
                    SpotDao spd = new SpotDao();
                    SpotLearnView.showPopularSpot(spd.getPopularSpots());
                    break;
            }
        } else {
            System.out.println("登录失败，你无法进入系统！");
        }
    }
}
