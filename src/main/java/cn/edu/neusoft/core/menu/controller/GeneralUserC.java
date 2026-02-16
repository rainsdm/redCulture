package cn.edu.neusoft.core.menu.controller;

import cn.edu.neusoft.controller.ManageAnnouncementC;
import cn.edu.neusoft.controller.ManagerRecordC;
import cn.edu.neusoft.domain.spot.role.generaluser.controller.SpotLearnC;
import cn.edu.neusoft.domain.user.role.generaluser.usercenter.controller.UserCenterC;
import cn.edu.neusoft.core.auth.statemachine.AuthEvents;
import cn.edu.neusoft.core.auth.statemachine.AuthStateMachine;
import cn.edu.neusoft.dao.SpotDao;
import cn.edu.neusoft.domain.spot.model.Spot;
import cn.edu.neusoft.domain.user.model.User;
import cn.edu.neusoft.view.IndexView;
import cn.edu.neusoft.domain.spot.role.generaluser.view.SpotLearnView;

import java.util.List;
import java.util.Scanner;

/**
 * 普通用户总控中心。
 */
public class GeneralUserC {
    private User loggedInUser;
    private AuthStateMachine asm;

    public GeneralUserC() {
    }

    public GeneralUserC(User loggedInUser, AuthStateMachine asm) {
        this.loggedInUser = loggedInUser;
        this.asm = asm;
    }

    public void routeMenu() {
        IndexView.indexOfGeneralUser();
        Scanner sc = new Scanner(System.in);
        UserCenterC userCenter = new UserCenterC();

        IO.print("请选择要进行的操作: ");
        int manu = sc.nextInt();
        sc.nextLine();

        // TODO: 导航业务必须单独拎出来，成为一个新的状态机。

        switch (manu) { // 这里只负责处理状态。
            case 0: // 退出系统。
                IO.println("您已退出登录。系统将回到登录界面。");
                loggedInUser = null; // 退出登录后，清空已登录用户的信息。
                asm.toggle(AuthEvents.REQUEST_LOGOUT);
                break;
            case 1: // 个人信息管理
                userCenter.managerCenter(loggedInUser);
                break;
            case 2: // 学习打卡
                // FIXME: 这里的代码很难测试到底层的部分，必须在未来重构。
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
                mr.manageRecord(loggedInUser);
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
    }
}
