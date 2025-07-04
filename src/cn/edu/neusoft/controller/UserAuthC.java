package cn.edu.neusoft.controller;

import cn.edu.neusoft.dao.UserDao;
import cn.edu.neusoft.model.User;
import cn.edu.neusoft.model.initPassword;
import cn.edu.neusoft.view.UserAuthView;

public class UserAuthC {
    /**
     * 根据用户在视图中获取到的信息，进行登录验证。
     * @return 登录成功后，返回来自数据库里的完整信息。否则，返回null。
     */
    public User login() {
        // 从登录视图获取用户的信息。
        User selectedUser = new User(UserAuthView.loginForm());

        /*
         * 比较这两个信息是否一致。需要做以下判断：
         * 判断是否输入了用户名与密码，再判断是否存在指定的用户。
         * 然后，判断密码是否一致，再判断角色信息的正确性。
         */

        if (selectedUser.getUsername().isEmpty()) {
            System.out.println("用户名不能为空！");
            return null;
        }

        if (selectedUser.getPassword().isEmpty()) {
            System.out.println("密码不能为空！");
            return null;
        }

        UserDao dm = new UserDao();
        User usrFromDB = dm.searchByUsername(selectedUser.getUsername());

        if (usrFromDB.getUser_id() == null) {
            System.out.println("不存在指定的用户。");
            return null;
        }

        if (usrFromDB.getPassword().equals(selectedUser.getPassword())) {
            System.out.println("登录成功！");
            return usrFromDB;
        } else {
            System.out.println("密码错误。");
            return null;
        }
    }

    // 判断是否已存在同样的用户名。
    public void register() {
        initPassword init = UserAuthView.registerForm();
        User registeredUser = null;

        while (!init.getPassword_1().equals(init.getPassword_2())) {
            System.out.println("两次输入的密码不一致！");
            init = null;
            init = UserAuthView.registerForm();
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