package cn.edu.neusoft.view;

import cn.edu.neusoft.model.User;
import cn.edu.neusoft.model.modPassword;

import java.util.Scanner;

public class CentreView {
    /**
     * 显示用户的个人信息，并且显示一个用于修改个人信息的菜单。
     * @param user 已成功登录的用户的个人信息。
     */
    public static void showInfo(User user) {
        System.out.println("==============个人中心================");
        System.out.println("用户ID：" + user.getUser_id());
        System.out.println("用户名：" + user.getUsername());
        System.out.println("密码：" + user.getPassword());
        System.out.println("角色：" + user.roleToString());
        System.out.println("学分：" + user.getStudy_points());
    }

    public static void showOperator() {
        System.out.println("-------------------------------------");
        System.out.println("1 查看个人信息");
        System.out.println("2 修改个人信息");
        System.out.println("0 返回上一级");
    }

    /**
     * 修改用户密码。
     * @param usr 待修改的用户信息。
     * @return 修改后密码数组。
     */
    public static modPassword modifyPassword(User usr) {
        modPassword modify = new modPassword(usr.getUser_id());

        System.out.println("==============个人中心================");
        System.out.println("--------------修改密码----------------");
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入原始密码：");
        String originPassword = sc.nextLine();
        modify.setOldPassword(originPassword);

        System.out.println("请输入新密码：");
        String newPass1 = sc.nextLine();

        System.out.println("请确认密码：");
        String newPass2 = sc.nextLine();

        if (newPass1.equals(newPass2)) {
            modify.setNewPassword(newPass1);
        } else {
            System.out.println("两次输入的密码不一致。");
            modify.setNewPassword("");
        }
        return modify;
    }
}
