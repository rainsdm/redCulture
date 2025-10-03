package cn.edu.neusoft.controller;

import cn.edu.neusoft.model.User;
import cn.edu.neusoft.usersession.SessionDispatcher;
import cn.edu.neusoft.usersession.statemachine.auth.AuthEvents;
import cn.edu.neusoft.usersession.statemachine.auth.AuthStateMachine;
import cn.edu.neusoft.view.UserAuthView;

public class MainC {
	/**
	 * 认证状态机的实例。
	 */
	private final AuthStateMachine asm;

	/**
	 * 存储已登录用户的信息。在全局范围内可用。
	 */
	private User loggedInUser; // TODO: 可以在将来，让它成为全局单实例，并且在部署时，规定它必须是一个单实例，强制程序单线程运行。

	public MainC() {
		this.asm = new AuthStateMachine();
		loggedInUser = null;
	}

	/**
	 * 应用主入口。
	 */
	public void startApp() {
		boolean isRunning = true;

		while (isRunning) {
			switch (asm.getCurrentState()) {
			case NOT_AUTHENTICATED:
				isRunning = homePageInputProc();
				break;
			case AUTHENTICATED:
				userSession();
				break;
			}
		}

		System.out.println("感谢使用，程序已退出。");
		System.exit(0);
	}

	/**
	 * 负责处理用户在首页的输入信息，并决定是否继续整个应用。
	 * 
	 * @return 循环是否持续的信号。true表示继续允许，false表示退出应用。
	 */
	private boolean homePageInputProc() {
		UserAuthC uac = new UserAuthC();
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
			return false; // 当接收到退出信号时，退出循环。
		}

		return true; // 其他情况下，默认让循环继续。
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
