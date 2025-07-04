package cn.edu.neusoft.controller;

import cn.edu.neusoft.dao.UserDao;
import cn.edu.neusoft.model.User;
import cn.edu.neusoft.model.initPassword;
import cn.edu.neusoft.view.LoginView;

public class RegisterC {
    // 判断是否已存在同样的用户名。
    public void register() {
        initPassword init = LoginView.registerForm();
        User registeredUser = null;

        while (!init.getPassword_1().equals(init.getPassword_2())) {
            System.out.println("两次输入的密码不一致！");
            init = null;
            init = LoginView.registerForm();
        }

        registeredUser = new User(init.getUserName(), init.getPassword_2(), init.getRole());

        UserDao ud = new UserDao();

        User isUserExists = ud.searchByUsername(registeredUser.getUsername());

        if (isUserExists.getUsername() != null &&
                isUserExists.getUsername().equals(registeredUser.getUsername())) {
            System.out.println("用户名已经存在。中断注册流程。");
            return;
        }
        if (registeredUser.getPassword().isEmpty()) {
            System.out.println("密码不能为空");
        } else {
            int status = ud.insertUser(registeredUser);
            if (status == 1) {
                System.out.println("注册成功");
                // 原计划成功后，直接进入到登录流程。
            } else {
                System.out.println("注册失败。");
            }
        }
    }
}
