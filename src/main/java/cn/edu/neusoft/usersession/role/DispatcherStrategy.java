package cn.edu.neusoft.usersession.role;

import cn.edu.neusoft.model.User;
import cn.edu.neusoft.statemachine.auth.AuthStateMachine;

public interface DispatcherStrategy {

	/**
	 * 接收到的用户信息。
	 * @param loggedInUser
	 * @param asm
	 */
	void recive(User loggedInUser, AuthStateMachine asm);

}