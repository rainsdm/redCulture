package cn.edu.neusoft.domain.user.role.generaluser.usercenter.view;

import cn.edu.neusoft.model.InputtedPassword;
import cn.edu.neusoft.domain.user.model.User;

import java.util.Scanner;

public class CentreView {
    /**
     * 显示用户的个人信息，并且显示一个用于修改个人信息的菜单。
     *
     * @param user 已成功登录的用户的个人信息。
     */
    public static void showInfo(User user) {
        IO.println("==============个人中心================");
        IO.println("用户ID：" + user.getUserId());
        IO.println("用户名：" + user.getUsername());
        IO.println("密码：" + "******");
        IO.println("角色：" + user.roleToString());
        IO.println("学分：" + user.getStudyPoints());
    }

    /**
     * 显示个人信息管理功能的最顶层菜单。
     */
    public static void showUserManageOperator() {
        IO.println("-------------------------------------");
        IO.println("1 查看个人信息");
        IO.println("2 修改密码");
        IO.println("0 返回上一级");
    }

    /**
     * 修改用户密码。
     *
     * @return 修改密码请求。
     */
    public static InputtedPassword modifyPassword() {
        IO.println("==============个人中心================");
        IO.println("--------------修改密码----------------");
        Scanner sc = new Scanner(System.in);
        IO.println("请输入原始密码：");
        String originPassword = sc.nextLine();

        IO.println("请输入新密码：");
        String newPass1 = sc.nextLine();

        IO.println("请确认密码：");
        String newPass2 = sc.nextLine();

        while (!newPass1.equals(newPass2)) {
            IO.println("两次输入的密码不一致。");
            IO.println("请重新输入新密码：");
            newPass1 = sc.nextLine();

            IO.println("请重新确认密码：");
            newPass2 = sc.nextLine();
        }

        return new InputtedPassword(originPassword, newPass1);
    }
}
