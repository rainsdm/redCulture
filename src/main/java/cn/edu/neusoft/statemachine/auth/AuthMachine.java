package cn.edu.neusoft.statemachine.auth;

/**
 * 管理用户的在线及注册状态
 */
public class AuthMachine {
    /**
     * &emsp;&emsp;这个字段决定是否需要退出程序。当它等于0时，程序正常退出。
     * 否则，根据程序状态，执行对应的流程。<br>
     * &emsp;&emsp;它只能由控制器来改变。
     */
    private int CURRENT_STATE;
}
