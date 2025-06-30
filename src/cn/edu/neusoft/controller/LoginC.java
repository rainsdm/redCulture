package cn.edu.neusoft.controller;

import cn.edu.neusoft.dao.UserDao;
import cn.edu.neusoft.model.User;
import cn.edu.neusoft.view.IndexView;
import cn.edu.neusoft.view.LoginView;

public class LoginC {
    public static void login() {
        // 从登录视图获取用户的信息。
        User selectedUser = new User(LoginView.loginForm());

        /*
         * 比较这两个信息是否一致。需要做以下判断：
         * 判断是否输入了用户名与密码，再判断是否存在指定的用户。
         * 然后，判断密码是否一致，再判断角色信息的正确性。
         */

        if (selectedUser.getUsername().isEmpty()) {
            System.out.println("用户名不能为空！");
            return;
        }

        if (selectedUser.getPassword().isEmpty()) {
            System.out.println("密码不能为空！");
            return;
        }

        UserDao dm = new UserDao();
        User usrFromDB = dm.searchByUsername(selectedUser.getUsername());

        if (usrFromDB.getUser_id() == null) {
            System.out.println("不存在指定的用户。");
            return;
        }

        if (usrFromDB.getPassword().equals(selectedUser.getPassword())) {
            if (usrFromDB.roleToString().equals(selectedUser.roleToString())) {
                System.out.println("登录成功！");
                if (usrFromDB.roleToString().equals("管理员")) {
                    IndexView.indexOfAdmin(usrFromDB);
                } else {
                    IndexView.indexOfGeneralUser(usrFromDB);
                }
            } else {
                System.out.println("角色信息错误，登录失败。");
            }
        } else {
            System.out.println("密码不正确，登录失败。");
        }
    }
}