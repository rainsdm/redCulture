package cn.edu.neusoft.usersession.role;

import cn.edu.neusoft.model.User;
import cn.edu.neusoft.statemachine.auth.AuthStateMachine;
import cn.edu.neusoft.usersession.controller.GeneralUserC;

/**
 * 普通用户的会话策略。
 */
public class GeneralUser implements DispatcherStrategy {
	@Override
	public void recive(User loggedInUser, AuthStateMachine asm) {
		GeneralUserC guc = new GeneralUserC(loggedInUser, asm);
		guc.routeMenu();
	}
}
