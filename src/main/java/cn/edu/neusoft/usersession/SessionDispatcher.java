package cn.edu.neusoft.usersession;

import cn.edu.neusoft.usersession.role.DispatcherStrategy;

public class SessionDispatcher {
	DispatcherStrategy dispatcher;
	
	public SessionDispatcher(DispatcherStrategy dispatcher) {
		this.dispatcher = dispatcher;
	}
	
	/**
	 * 按照用户选择的角色来分发对应的策略。
	 * @param role 0代表管理员，1代表普通用户。
	 */
	public void selectedRole(int role) {
		dispatcher.recive(role);
	}
}
