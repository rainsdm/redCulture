package cn.edu.neusoft.view;

import cn.edu.neusoft.model.User;
import cn.edu.neusoft.model.initPassword;

import java.util.Scanner;

/**
 * 这个类负责实现用户注册、登录验证的控制流程。
 */
public class UserAuthView {
    /**
     * 由用户决定是登录还是注册。
     *
     * @return 用户最终选择的菜单。
     */
    public static int chooseLoginMethod() {
        System.out.println("==========================");
        System.out.println("红色文化学习打卡系统");
        System.out.println("==========================");

        System.out.println("1 登录");
        System.out.println("2 注册");
        System.out.println("0 退出");

        Scanner sc = new Scanner(System.in);
        System.out.print("请使用数字选择要进行的操作: ");
        return sc.nextInt();
    }

    public static User loginForm() {
        User user = new User();

        System.out.println("==========================");
        System.out.println("红色文化学习打卡系统");
        System.out.println("登录页");
        System.out.println("==========================");

        Scanner sc = new Scanner(System.in);
        System.out.print("输入用户名: ");
        user.setUsername(sc.nextLine());
        System.out.print("输入密码: ");
        user.setPassword(sc.nextLine());
//        System.out.print("输入角色, 0 管理员 1 普通用户: ");
//        user.setRole(sc.nextInt());
//        sc.nextLine();

        return user;
    }

    public static initPassword registerForm() {
        System.out.println("==========================");
        System.out.println("红色文化学习打卡系统");
        System.out.println("注册页");
        System.out.println("==========================");

        Scanner sc = new Scanner(System.in);

        System.out.print("请输入用户名: ");
        String username = sc.nextLine();

        System.out.print("请输入密码: ");
        String password1 = sc.nextLine();

        System.out.print("请确认密码：");
        String password2 = sc.nextLine();


        System.out.print("请输入角色, 0 管理员 1 普通用户: ");
        int role = sc.nextInt();

        return new initPassword(username, password1, password2, role);
    }
}
