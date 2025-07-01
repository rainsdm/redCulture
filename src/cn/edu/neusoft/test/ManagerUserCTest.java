package cn.edu.neusoft.test;

import cn.edu.neusoft.controller.ManagerUserC;
import cn.edu.neusoft.view.ManageUserView;

public class ManagerUserCTest {
    public static void main(String[] args) {
        ManagerUserC manager = new ManagerUserC();
//        manager.showAllUsers();
//        manager.searchByNameC();
//        manager.searchByIdC();
//        manager.searchByRoleC();
        manager.searchUsersC();
//        manager.addUser(ManageUserView.addUserView());
//        manager.deleteUser(ManageUserView.deleteUserView());
    }
}
