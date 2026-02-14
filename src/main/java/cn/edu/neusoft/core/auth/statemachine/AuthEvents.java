package cn.edu.neusoft.core.auth.statemachine;

public enum AuthEvents {
    /**
     * 用户尝试登录
     */
    ATTEMPT_LOGIN,

    /**
     * 尝试注册新用户
     */
    ATTEMPT_REGISTER,

    /**
     * 用户发出退出登录请求
     */
    REQUEST_LOGOUT
}
