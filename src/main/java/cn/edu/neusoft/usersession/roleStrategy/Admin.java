package cn.edu.neusoft.usersession.roleStrategy;

import cn.edu.neusoft.model.User;
import cn.edu.neusoft.usersession.controller.AdminC;
import cn.edu.neusoft.core.auth.statemachine.AuthStateMachine;

/**
 * 管理员用户的会话策略。
 */
public class Admin implements DispatcherStrategy {

	@Override
	public void recive(User loggedInUser, AuthStateMachine asm) {
		AdminC admin = new AdminC(loggedInUser, asm);
		admin.routeMenu();
	}

}
