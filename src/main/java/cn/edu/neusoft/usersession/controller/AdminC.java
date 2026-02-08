package cn.edu.neusoft.usersession.controller;

import java.util.Scanner;

import cn.edu.neusoft.controller.ManageAnnouncementC;
import cn.edu.neusoft.controller.ManageSpotC;
import cn.edu.neusoft.controller.ManagerRecordC;
import cn.edu.neusoft.controller.ManagerUserC;
import cn.edu.neusoft.dao.SpotDao;
import cn.edu.neusoft.model.Announcement;
import cn.edu.neusoft.model.User;
import cn.edu.neusoft.core.auth.statemachine.AuthEvents;
import cn.edu.neusoft.core.auth.statemachine.AuthStateMachine;
import cn.edu.neusoft.view.AnnouncementManageView;
import cn.edu.neusoft.view.IndexView;
import cn.edu.neusoft.view.ManageSpotView;
import cn.edu.neusoft.view.ManageUserView;
import cn.edu.neusoft.view.SpotLearnView;

/**
 * 管理员总控中心。
 */
public class AdminC {
	private User loggedInUser;
	private AuthStateMachine asm;

	public AdminC() {
	}

	public AdminC(User loggedInUser, AuthStateMachine asm) {
		this.loggedInUser = loggedInUser;
		this.asm = asm;
	}

	public void routeMenu() {
		IndexView.indexOfAdmin();
		Scanner sc = new Scanner(System.in);
		int menu = sc.nextInt();
		sc.nextLine();
		ManagerUserC managerUser = new ManagerUserC();
		ManageSpotC manageSpot = new ManageSpotC();
		int operator;

		// TODO: 导航业务必须单独拎出来，成为一个新的状态机。

		switch (menu) {
		case 0:
			IO.println("您已退出登录。系统将回到登录界面。");
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
					IO.println("景点添加成功! ");
				} else {
					IO.println("景点添加失败! ");
				}
				break;
			case 2: // 删除景点
				if (manageSpot.deleteSpot()) {
					IO.println("景点删除成功! ");
				} else {
					IO.println("景点删除失败! ");
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
			mr.manageRecord(loggedInUser);
			break;
		case 5: // 查看热门景点排行
			SpotDao spd = new SpotDao();
			SpotLearnView.showPopularSpot(spd.getPopularSpots());
			break;
		}
	}
}
