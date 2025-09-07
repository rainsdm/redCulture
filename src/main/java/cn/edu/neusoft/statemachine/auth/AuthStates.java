package cn.edu.neusoft.statemachine.auth;


public enum AuthStates {
	/**
	 * 用户未登录，可以自主选择注册为新用户，或者以老用户身份登录。
	 */
	NOT_AUTHENTICATED,
	
	/**
	 * 用户已登录，应该进入已登录用户的会话页面。
	 */
	AUTHENTICATED
}
