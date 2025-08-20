package cn.edu.neusoft.controller;

import cn.edu.neusoft.dao.UserDao;
import cn.edu.neusoft.dto.user.auth.request.CreateUserRequest;
import cn.edu.neusoft.dto.user.auth.request.loginRequest;
import cn.edu.neusoft.dto.user.auth.response.UserAuthInfo;
import cn.edu.neusoft.model.User;
import cn.edu.neusoft.service.auth.user.Register;
import cn.edu.neusoft.view.UserAuthView;

public class UserAuthC {
    /**
     * 根据用户在视图中获取到的信息，进行登录验证。
     *
     * @return 登录成功后，返回来自数据库里的完整信息。否则，返回null。
     */
    public User login() {
        // 从登录视图获取用户的信息。
        loginRequest selectedUser = UserAuthView.loginForm();

        UserDao dm = new UserDao();
        UserAuthInfo usrFromDB = dm.findAuthInfoByUsername(selectedUser.username());

        if (usrFromDB.username().isBlank()) {
            System.out.println("用户不存在。");
            return null;
        }

        if (usrFromDB.password().equals(selectedUser.password())) {
            System.out.println("登录成功！");
            return dm.findByUsernameWithTimestamps(usrFromDB.username());
        } else {
            System.out.println("密码错误，登录失败。");
            return null;
        }
    }


    /**
     * 控制普通用户的注册流程。
     */
    public void register() {
        CreateUserRequest registerForm = UserAuthView.registerForm();

        //<editor-fold desc = "业务逻辑">
        Register userRegister = new Register();

        int status = userRegister.handle(registerForm);
        //</editor-fold>

        //<editor-fold desc = "判断是否注册成功。">
        if (status == 1) {
            System.out.println("注册成功");
            // 原计划成功后，直接进入到登录流程。
        } else {
            System.out.println("注册失败。");
        }
        //</editor-fold>
    }
}