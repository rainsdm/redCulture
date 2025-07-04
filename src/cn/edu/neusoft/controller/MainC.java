package cn.edu.neusoft.controller;

import cn.edu.neusoft.model.User;
import cn.edu.neusoft.view.IndexView;
import cn.edu.neusoft.view.UserAuthView;

import java.util.Scanner;

public class MainC {
    /**
     * &emsp;&emsp;这个字段决定是否需要退出程序。当它等于0时，程序正常退出。
     * 否则，根据程序状态，执行对应的流程。
     */
    private static int CURRENT_STATE = 0;
    /**
     * 用户已登录，应该进入已登录用户的会话页面。
     */
    private static final int STATE_USER_LOGGED = 1;
    /**
     * 用户未登录，需要注册为新用户，或者作为老用户登录。
     */
    private static final int STATE_LOGIN_FLOW = 2;
    /**
     * 程序即将退出。
     */
    private static final int STATE_EXIST = 0;
    /**
     * 存储已登录用户的信息。在全局范围内可用。
     */
    User loggedInUser;

    public MainC() {
        CURRENT_STATE = STATE_LOGIN_FLOW;
        loggedInUser = null;
    }

    /**
     * 对外操作的真正入口。
     */
    public void mainLoop() {
        boolean shouldLogin = true;
        while (CURRENT_STATE != STATE_EXIST) {
            switch (CURRENT_STATE) {
                case STATE_USER_LOGGED:
                    userSession();
                    break;
                case STATE_LOGIN_FLOW:
                    loginFlow();
                    break;
            }
        }
        System.out.println("感谢使用，程序已退出。");
        System.exit(0);
    }

    /**
     * 项目的注册、登录入口。
     */
    private void loginFlow() {
        CURRENT_STATE = STATE_LOGIN_FLOW;
        UserAuthC auth = new UserAuthC();

        int login_menu = UserAuthView.chooseLoginMethod();
            switch (login_menu) {
                case 1:
                    loggedInUser = auth.login();
                    CURRENT_STATE = STATE_USER_LOGGED;
                    break;
                case 2:
                    auth.register(); // 注册完成后，直接进入登录流程。
                    break;
                case 0:
                    CURRENT_STATE = STATE_EXIST;
                    break;
            }
        if (loggedInUser != null && loggedInUser.getUser_id() != null && !loggedInUser.getUser_id().isEmpty()) {
            // 如果登录成功，在循环外面开启已登录用户的会话信息。
            CURRENT_STATE = STATE_USER_LOGGED;
        }
    }

    private void userSession() {
        if (loggedInUser != null && loggedInUser.getRole() == 1) {
            CURRENT_STATE = STATE_USER_LOGGED;
            // 跳转到普通用户的首页
            IndexView.indexOfGeneralUser();
            Scanner sc = new Scanner(System.in);
            UserCenterC userCenter = new UserCenterC();

            boolean sessionActive = true;

            while (sessionActive) {
                System.out.print("请选择要进行的操作: ");
                int manu = sc.nextInt();
                sc.nextLine();
                switch (manu) {
                    case 0:
                        sessionActive = false;
                        System.out.println("您已退出登录。系统将回到登录界面。");
                        CURRENT_STATE = STATE_LOGIN_FLOW;
                        break;
                    case 1:
                        userCenter.managerCenter(loggedInUser);
                        break;
                }
            }
        } else if (loggedInUser != null && loggedInUser.getRole() == 0) {
            // 跳转到管理员用户的首页
            System.out.println(loggedInUser.getUsername());
        } else {
            System.out.println("登录失败，你无法进入系统！");
        }
    }
}
