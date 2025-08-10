package cn.edu.neusoft.view;

import cn.edu.neusoft.dto.user.request.RegisterUserInfo;
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

    /**
     * 普通用户的注册功能。
     * @return 含有注册信息的dto。
     */
    public static RegisterUserInfo registerForm() {
        System.out.println("==========================");
        System.out.println("红色文化学习打卡系统");
        System.out.println("注册页");
        System.out.println("==========================");

        Scanner sc = new Scanner(System.in);

        System.out.print("请输入用户名: ");  //TODO: 缺少用户名的校验，用户名可能是空的。
        String username = sc.nextLine();

        String password1;
        String password2;
        String finalPassword = null;

        boolean isDifferentPassword = true;
        while (isDifferentPassword) {
            System.out.print("请输入密码: ");
            password1 = sc.nextLine();

            System.out.print("请确认密码：");
            password2 = sc.nextLine();

            isDifferentPassword = !(password1.equals(password2));
            if (isDifferentPassword) {
                System.out.println("两次输入的密码不一致，请重新输入密码。");
            } else {
                finalPassword = password1;
            }

            //TODO: 还可以加入密码强度的校验规则。
        }

        return new RegisterUserInfo(username, finalPassword, 1);
    }
}
