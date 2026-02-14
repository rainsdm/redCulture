package cn.edu.neusoft.core.router;

import cn.edu.neusoft.core.auth.statemachine.AuthStateMachine;
import cn.edu.neusoft.core.router.roleStrategy.AdminRoute;
import cn.edu.neusoft.core.router.roleStrategy.GeneralUserRoute;
import cn.edu.neusoft.core.router.roleStrategy.RouteStrategy;
import cn.edu.neusoft.model.User;

/**
 * 这是会话策略的上下文。它负责根据用户的角色，采用对应角色应该使用的具体策略。
 */
public class AppRouter {
    private final User loggedInUser;
    private final AuthStateMachine asm;

    public AppRouter(User loggedInUser, AuthStateMachine asm) {
        this.loggedInUser = loggedInUser;
        this.asm = asm;
    }

    /**
     * 从接收到的用户信息中，检查用户角色。然后按照用户的角色类别，分别实例化为不同的控制器。 被实例化的控制器共享至少同一套接口。
     */
    public void dispatch() {
        RouteStrategy dispatcher = null;
        if (loggedInUser.getRole() == 0) {
            dispatcher = new AdminRoute();
        } else if (loggedInUser.getRole() == 1) {
            dispatcher = new GeneralUserRoute();
        }

        dispatcher.recive(loggedInUser, asm);
    }
}
