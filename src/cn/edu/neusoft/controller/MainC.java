package cn.edu.neusoft.controller;

import cn.edu.neusoft.dao.SpotDao;
import cn.edu.neusoft.model.Spot;
import cn.edu.neusoft.model.User;
import cn.edu.neusoft.view.IndexView;
import cn.edu.neusoft.view.SpotLearnView;
import cn.edu.neusoft.view.UserAuthView;

import java.util.List;
import java.util.Scanner;

public class MainC {
    /**
     * 用户已登录，应该进入已登录用户的会话页面。
     */
    public static final int STATE_USER_LOGGED = 1;
    /**
     * 用户未登录，需要注册为新用户，或者作为老用户登录。
     */
    public static final int STATE_LOGIN_FLOW = 2;
    /**
     * 退出程序。
     */
    public static final int STATE_EXIST = 0;
    /**
     * 存储已登录用户的信息。在全局范围内可用。
     */
    User loggedInUser;
    /**
     * &emsp;&emsp;这个字段决定是否需要退出程序。当它等于0时，程序正常退出。
     * 否则，根据程序状态，执行对应的流程。
     */
    private int CURRENT_STATE;

    public MainC() {
        CURRENT_STATE = STATE_LOGIN_FLOW;
        loggedInUser = null;
    }

    /**
     * 对外操作的真正入口。
     */
    public void mainLoop() {
        while (CURRENT_STATE != STATE_EXIST) {
            switch (CURRENT_STATE) {
                case STATE_USER_LOGGED:
                    userSession();
                    break;
                case STATE_LOGIN_FLOW:
                    loginFlow();
                    break;
            }
        }
        System.out.println("感谢使用，程序已退出。");
        System.exit(0);
    }

    /**
     * 项目的注册、登录入口。
     */
    private void loginFlow() {
        CURRENT_STATE = STATE_LOGIN_FLOW;
        UserAuthC auth = new UserAuthC();

        int login_menu = UserAuthView.chooseLoginMethod();
        switch (login_menu) {
            case 1:
                loggedInUser = auth.login();
                if (loggedInUser != null && loggedInUser.getUser_id() != null
                        && !loggedInUser.getUser_id().isEmpty()) {
                    // 进行严格的登录检查。只有当它确实不为空，且取到了有效的数据时，才会正常开启会话。
                    CURRENT_STATE = STATE_USER_LOGGED;
                }
                break;
            case 2:
                auth.register(); // 注册完成后，直接进入登录流程。
                break;
            case 0:
                CURRENT_STATE = STATE_EXIST;
                loggedInUser = null; // 退出登录后，清空已登录用户的信息。
                break;
        }
    }

    private void userSession() {
        if (loggedInUser != null && loggedInUser.getRole() == 1) {
            CURRENT_STATE = STATE_USER_LOGGED;
            // 跳转到普通用户的首页
            IndexView.indexOfGeneralUser();
            Scanner sc = new Scanner(System.in);
            UserCenterC userCenter = new UserCenterC();

            System.out.print("请选择要进行的操作: ");
            int manu = sc.nextInt();
            sc.nextLine();
            switch (manu) {
                case 0: // 退出系统
                    System.out.println("您已退出登录。系统将回到登录界面。");
                    loggedInUser = null; // 退出登录后，清空已登录用户的信息。
                    CURRENT_STATE = STATE_LOGIN_FLOW;
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
                    slc.showSpots(menu, loggedInUser.getUser_id());
                    break;
                case 3: // 管理学习记录
                    break;
            }
        } else if (loggedInUser != null && loggedInUser.getRole() == 0) {
            // 跳转到管理员用户的首页
            System.out.println(loggedInUser.getUsername());
        } else {
            System.out.println("登录失败，你无法进入系统！");
        }
    }
}
