package cn.edu.neusoft.controller;

import cn.edu.neusoft.dao.UserDao;
import cn.edu.neusoft.model.User;
import cn.edu.neusoft.view.ManageUserView;

import java.util.ArrayList;
import java.util.List;

public class ManagerUserC {
    UserDao ud = new UserDao();
    public void showAllUsers() {
        List<User> users = ud.searchAllUsers();

        ManageUserView.showAllUsersInfo(users);
    }

    private void showInfo(User usr) {
        System.out.println("用户ID: "+ usr.getUser_id());
        System.out.println("用户名: "+ usr.getUsername());
        System.out.println("用户密码: "+ usr.getPassword());
        System.out.println("用户角色: " + usr.getRole());
        System.out.println("用户学习积分: "+ usr.getStudy_points());
    }

    //<editor-fold desc="新增用户">
    /**
     * 向数据库增加新用户。
     *
     * @param usr 待增加的用户信息。
     */
    public void addUser(User usr) {
        int result;
        if (usr.getPassword() == null || usr.getPassword().isEmpty()) {
            result = 0;
            System.out.println("密码不匹配，用户新增失败！");
            return;
        } else {
            result = ud.insertUser(usr);
        }
        if (result > 0) {
            System.out.println("新增成功! ");
        } else {
            System.out.println("新增失败! ");
        }
    }
    //</editor-fold>

    //<editor-fold desc="查找用户">
    public void searchByNameC() {
        User user = new User(ud.searchByUsername(ManageUserView.searchByNameView()));
        showInfo(user);
    }

    public void searchByIdC() {
        User user = new User(ud.searchByUserID(ManageUserView.searchByIdView()));
        showInfo(user);
    }

    public void searchByRoleC() {
        List<User> users = new ArrayList<>();
        String role = ManageUserView.searchByRole();
        users = ud.searchByRole(role);
        ManageUserView.showAllUsersInfo(users);
    }
    //</editor-fold>


}
