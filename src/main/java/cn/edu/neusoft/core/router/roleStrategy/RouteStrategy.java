package cn.edu.neusoft.core.router.roleStrategy;

import cn.edu.neusoft.core.auth.statemachine.AuthStateMachine;
import cn.edu.neusoft.domain.user.model.User;

public interface RouteStrategy {

    /**
     * 接收到的用户信息。
     *
     * @param loggedInUser
     * @param asm
     */
    void recive(User loggedInUser, AuthStateMachine asm);

}