package cn.edu.neusoft.controller;

import java.util.ArrayList;
import java.util.List;

import cn.edu.neusoft.dao.RecordDao;
import cn.edu.neusoft.dao.UserDao;
import cn.edu.neusoft.model.User;
import cn.edu.neusoft.service.auth.admin.Register;
import cn.edu.neusoft.usersession.dto.user.auth.request.CreateUserRequest;
import cn.edu.neusoft.view.ManageUserView;

/**
 * 这个类包含了所有由管理员对用户进行管理的方法。
 */
public class ManagerUserC {
	UserDao ud = new UserDao();
	RecordDao rd = new RecordDao();

	public void showAllUsers() {
		List<User> users = ud.searchAllUsers();

		ManageUserView.showAllUsersInfo(users);
	}

	private void showInfo(User usr) {
		System.out.println("用户ID: " + usr.getUserId());
		System.out.println("用户名: " + usr.getUsername());
		System.out.println("用户密码: " + "******");
		System.out.println("用户角色: " + usr.roleToString());
		System.out.println("用户学习积分: " + usr.getStudyPoints());
	}

	// <editor-fold desc="新增用户">
	/**
	 * 向数据库增加新用户。
	 *
	 * @param newUsr 待增加的用户信息。
	 */
	public void addUser(CreateUserRequest newUsr) {
		Register adminRegister = new Register();
		int result = adminRegister.handle(newUsr);
		if (result > 0) {
			System.out.println("新增成功! ");
		} else {
			System.out.println("新增失败! ");
		}
	}
	// </editor-fold>

	// <editor-fold desc="删除用户">

	/**
	 * 根据用户名查出用户ID，然后根据用户ID删除对应的信息。
	 *
	 * @param username 待删除的用户名
	 */
	public void deleteUserC(String username) {
		// 首先判断数据库中是否存在指定的用户。如果存在，获取全部信息，然后根据ID删除用户。
		// 如果不存在，就结束操作，返回false。
		if (username == null || username.isEmpty()) {
			System.err.println("输入无效。用户名不能为空。");
			return;
		}
		int result = 0;
		User usr = ud.findByUsername(username); // TODO: 用id来查找更合理。用户名有可能不再唯一。
		if (usr == null) {
			System.out.println("没有找到指定用户。");
		} else {
			result = ud.deleteUser(usr);
			if (result > 0) {
				System.out.println("用户删除成功。");
			} else {
				System.err.println("出现意外情况，没有按预期删除。");
			}
		}
	}
	// </editor-fold>

	// <editor-fold desc="查找用户">
	public void searchUsersC() {
		boolean flag = true;
		while (flag) {
			int menu = ManageUserView.searchView();
			switch (menu) {
			case 1:
				showAllUsers();
				break;
			case 2:
				searchByNameC();
				break;
			case 3:
				searchByIdC();
				break;
			case 4:
				searchByRoleC();
				break;
			default:
				flag = false;
				break;
			}
		}
	}

	public void searchByNameC() {
		User user = new User(ud.findByUsername(ManageUserView.searchByNameView()));
		showInfo(user);
	}

	public void searchByIdC() {
		User user = new User(ud.searchByUserID(ManageUserView.searchByIdView()));
		showInfo(user);
	}

	public void searchByRoleC() {
		List<User> users = new ArrayList<>();
		String role = ManageUserView.searchByRole();
		users = ud.searchByRole(role);
		ManageUserView.showAllUsersInfo(users);
	}
	// </editor-fold>

}
