package cn.edu.neusoft.controller;

import cn.edu.neusoft.model.User;
import cn.edu.neusoft.statemachine.auth.AuthEvents;
import cn.edu.neusoft.statemachine.auth.AuthStateMachine;
import cn.edu.neusoft.usersession.controller.AdminC;
import cn.edu.neusoft.usersession.controller.GeneralUserC;
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

    public User getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * 对外操作的真正入口。
     */
    public void startApp() {
    	boolean isRunning = true;

    	while (isRunning) {
    		UserAuthC uac = new UserAuthC();
			switch (asm.getCurrentState()) {
			case NOT_AUTHENTICATED:
				int selection = UserAuthView.chooseLoginMethod(); //TODO: 可以用枚举来代替魔法常量，尽管魔法常量比魔法数字更好。
				final int exit = 0;
				final int inLogin = 1;
				final int inRegister = 2;
				
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
    	int admin = 0;
    	int generalUser = 1;
    	AdminC ac = new AdminC(this.loggedInUser, this.asm);
    	GeneralUserC guc = new GeneralUserC(this.loggedInUser, this.asm);
        if (loggedInUser != null && loggedInUser.getRole() == generalUser) {
            guc.routeMenu();
        } else if (loggedInUser != null && loggedInUser.getRole() == admin) {
            ac.routeMenu();
        } else {
            System.out.println("登录失败，你无法进入系统！");
        }
    }
}
