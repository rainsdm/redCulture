package cn.edu.neusoft.core.router.roleStrategy;

import cn.edu.neusoft.core.auth.statemachine.AuthStateMachine;
import cn.edu.neusoft.core.menu.controller.AdminC;
import cn.edu.neusoft.model.User;

/**
 * 管理员用户的会话策略。
 */
public class AdminRoute implements RouteStrategy {

    @Override
    public void recive(User loggedInUser, AuthStateMachine asm) {
        AdminC admin = new AdminC(loggedInUser, asm);
        admin.routeMenu();
    }

}
