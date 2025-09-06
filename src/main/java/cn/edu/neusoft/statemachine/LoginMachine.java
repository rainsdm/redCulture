package cn.edu.neusoft.statemachine;

/**
 * 管理用户的在线及注册状态
 */
public class LoginMachine {
    /**
     * 用户已登录，应该进入已登录用户的会话页面。
     */
    private static final int STATE_USER_AUTHENTICATED = 1;
    /**
     * 用户未登录，需要注册为新用户，或者作为老用户登录。
     */
    private static final int STATE_OFFLINE = 2;
    /**
     * 退出程序。
     */
    private static final int STATE_EXIST = 0;
    /**
     * &emsp;&emsp;这个字段决定是否需要退出程序。当它等于0时，程序正常退出。
     * 否则，根据程序状态，执行对应的流程。<br>
     * &emsp;&emsp;它只能由控制器来改变。
     */
    private int CURRENT_STATE;
}
