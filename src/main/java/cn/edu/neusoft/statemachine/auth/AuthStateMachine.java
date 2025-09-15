package cn.edu.neusoft.statemachine.auth;

/**
 * 管理认证状态的状态机
 */
public class AuthStateMachine {
    
    private AuthStates currentState;

    /**
     * 初始化管理认证状态的状态机。
     */
    public AuthStateMachine() {
        this.currentState = AuthStates.NOT_AUTHENTICATED;
    }

    public AuthStates getCurrentState() {
        return this.currentState;
    }

    /**
     * 对外用于改变状态。
     * @param event 认证时对应的几个事件。
     */
    public void toggle(AuthEvents event) {
        this.currentState = this.currentState.transition(event);
    }
}
