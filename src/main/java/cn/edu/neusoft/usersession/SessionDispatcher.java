package cn.edu.neusoft.usersession;

import cn.edu.neusoft.model.User;
import cn.edu.neusoft.statemachine.auth.AuthStateMachine;
import cn.edu.neusoft.usersession.role.Admin;
import cn.edu.neusoft.usersession.role.DispatcherStrategy;
import cn.edu.neusoft.usersession.role.GeneralUser;

public class SessionDispatcher {
	private final User loggedInUser;
	private final AuthStateMachine asm;
	
	public SessionDispatcher(User loggedInUser, AuthStateMachine asm) {
		this.loggedInUser = loggedInUser;
		this.asm = asm;
	}
	
	/**
	 * 从接收到的用户信息中，检查用户角色。然后按照用户的角色类别，分别实例化为不同的控制器。
	 * 被实例化的控制器共享至少同一套接口。
	 */
	public void dispatch() {
		DispatcherStrategy dispatcher = null;
		if (loggedInUser.getRole() == 0) {
			dispatcher = new Admin();
		} else if (loggedInUser.getRole() == 1) {
			dispatcher = new GeneralUser();
		}
		
		dispatcher.recive(loggedInUser, asm);
	}
}
