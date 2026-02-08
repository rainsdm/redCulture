package cn.edu.neusoft.core.auth.statemachine;

public enum AuthStates {
	/**
	 * 用户未登录，可以自主选择注册为新用户，或者以老用户身份登录。
	 */
	NOT_AUTHENTICATED {

		/**
		 * 自定义的状态转换逻辑。
		 */
		@Override
		public AuthStates transition(AuthEvents event) {
			switch (event) {
			case ATTEMPT_LOGIN:
//                case ATTEMPT_REGISTER: // 注册成功后，不会立即进入登录流程。用户还可以继续注册新的账号。
				return AUTHENTICATED;
			default:
				throw new IllegalArgumentException("无效的状态：" + event);
			}
		}
	},

	/**
	 * 用户已登录，应该进入已登录用户的会话页面。
	 */
	AUTHENTICATED {
		/**
		 * 自定义的状态转换逻辑。
		 */
		@Override
		public AuthStates transition(AuthEvents event) {
			switch (event) {
			case REQUEST_LOGOUT:
				return NOT_AUTHENTICATED;
			default:
				throw new IllegalArgumentException("无效的状态：" + event);
			}
		}
	};

	/**
	 * 通用的状态转换方法。每个常量都必须实现这个方法。
	 * 
	 * @param event 要影响认证领域状态的事件。
	 * @return 可以被改变的下一个状态。
	 */
	public abstract AuthStates transition(AuthEvents event);
}
