package cn.edu.neusoft.controller;

import cn.edu.neusoft.model.User;
import cn.edu.neusoft.statemachine.auth.AuthEvents;
import cn.edu.neusoft.statemachine.auth.AuthStateMachine;
import cn.edu.neusoft.usersession.SessionDispatcher;
import cn.edu.neusoft.view.*;

public class MainC {
	/**
	 * 认证状态机的实例。
	 */
	private final AuthStateMachine asm;
    
    /**
     * 存储已登录用户的信息。在全局范围内可用。
     */
    private User loggedInUser;

    public MainC() {
        this.asm = new AuthStateMachine();
        loggedInUser = null;
    }

    /**
     * 应用引擎。
     */
    public void startApp() {
    	boolean isRunning = true;

    	while (isRunning) {
    		UserAuthC uac = new UserAuthC();
			switch (asm.getCurrentState()) {
			case NOT_AUTHENTICATED:
				HomepageSelections selection = UserAuthView.chooseLoginMethod();
				
				switch (selection) {
				case inLogin:
					loggedInUser = uac.login();
					if (loggedInUser != null && loggedInUser.getUserId() != null) {
						asm.toggle(AuthEvents.ATTEMPT_LOGIN);
						userSession();
					}
					break;

				case inRegister:
					uac.register();
					break;
					
				case exit:
					isRunning = false;
					break;
				}
				break;

			case AUTHENTICATED:
				userSession();
				break;
			}
		}
        System.out.println("感谢使用，程序已退出。");
        System.exit(0);
    }

    private void userSession() {
        if (loggedInUser != null) {
			SessionDispatcher dispatcher = new SessionDispatcher(loggedInUser, asm);
			dispatcher.dispatch();
		} else {
            System.out.println("登录失败，你无法进入系统！");
        }
    }
}
