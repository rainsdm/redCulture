package cn.edu.neusoft.core.auth.controller;

import cn.edu.neusoft.core.auth.service.UserAuthenticator;
import cn.edu.neusoft.core.auth.service.user.Register;
import cn.edu.neusoft.domain.user.model.User;
import cn.edu.neusoft.view.UserAuthView;

public class UserAuthC {
    /**
     * 根据用户在视图中获取到的信息，进行登录验证。
     *
     * @return 登录成功后，返回来自数据库里的完整信息。否则，返回null。
     */
    public User login() {
        UserAuthenticator ua = new UserAuthenticator();
        return ua.authenticate(UserAuthView.loginForm());
    }

    /**
     * 控制普通用户的注册流程。
     */
    public void register() {
        Register userRegister = new Register();
        int status = userRegister.handle(UserAuthView.registerForm());

        if (status == 1) {
            IO.println("注册成功");
            // 原计划成功后，直接进入到登录流程。
        } else {
            IO.println("注册失败。");
        }
    }
}