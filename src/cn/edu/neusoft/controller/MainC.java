package cn.edu.neusoft.controller;

import cn.edu.neusoft.model.User;
import cn.edu.neusoft.view.UserAuthView;

public class MainC {
    /**
     * 项目的登录入口方法。
     */
    public void index() {
        User loggedInUser = new User(); // 存储已登录的用户信息。
        UserAuthC auth = new UserAuthC();

        boolean continueToLogin = true;
        while (continueToLogin) {
            int login_menu = UserAuthView.chooseLoginMethod();
            switch (login_menu) {
                case 1:
                    loggedInUser = auth.login();
                    continueToLogin = false;
                    break;
                case 2:
                    auth.register();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        }
    }
}
