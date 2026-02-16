package cn.edu.neusoft.controller;

import cn.edu.neusoft.core.auth.controller.UserAuthC;
import cn.edu.neusoft.core.auth.statemachine.AuthEvents;
import cn.edu.neusoft.core.auth.statemachine.AuthStateMachine;
import cn.edu.neusoft.core.router.AppRouter;
import cn.edu.neusoft.domain.user.model.User;
import cn.edu.neusoft.utils.appconfig.ConfigLoader;
import cn.edu.neusoft.utils.connector.SShTunnel;
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

        // 注册 JVM 关闭钩子
        // 这是一个“遗言机制”：无论程序是正常死、被 kill 死还是按 Ctrl+C 死，
        // 只要 JVM 开始关闭，这个线程就会被执行。
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            IO.println("\n[系统守护] 检测到应用正在退出，正在清理资源...");
            SShTunnel.close(); // 确保隧道断开
        }));

        ConfigLoader.init();
    }

    /**
     * 应用主入口。
     */
    public void startApp() {
        try {
            SShTunnel.establish();
        } catch (Exception e) {
            IO.println("SSH连接启动失败：" + e.getMessage());
            return; // 退出startApp
        }
        boolean isRunning = true;    // 用来判断是否停留在登录、注册页面，还是

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

        IO.println("感谢使用，程序已退出。");
        SShTunnel.close();
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
            AppRouter dispatcher = new AppRouter(loggedInUser, asm);
            dispatcher.dispatch();
        } else {
            IO.println("登录失败，你无法进入系统！");
        }
    }
}
