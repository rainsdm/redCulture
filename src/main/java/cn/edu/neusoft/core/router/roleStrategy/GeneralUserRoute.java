package cn.edu.neusoft.core.router.roleStrategy;

import cn.edu.neusoft.core.auth.statemachine.AuthStateMachine;
import cn.edu.neusoft.core.menu.controller.GeneralUserC;
import cn.edu.neusoft.model.User;

/**
 * 普通用户的会话策略。
 */
public class GeneralUserRoute implements RouteStrategy {
    @Override
    public void recive(User loggedInUser, AuthStateMachine asm) {
        GeneralUserC guc = new GeneralUserC(loggedInUser, asm);
        guc.routeMenu();
    }
}
