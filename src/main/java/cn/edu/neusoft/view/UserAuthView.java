package cn.edu.neusoft.view;

import cn.edu.neusoft.controller.HomepageSelections;
import cn.edu.neusoft.core.auth.dto.request.CreateUserRequest;
import cn.edu.neusoft.core.auth.dto.request.loginRequest;

import java.util.Scanner;

/**
 * 这个类负责实现用户注册、登录验证的控制流程。
 */
public class UserAuthView {
    /**
     * 由用户决定是登录还是注册。
     *
     * @return 用户最终选择的菜单。有可能是null。
     */
    public static HomepageSelections chooseLoginMethod() {
        IO.println("==========================");
        IO.println("红色文化学习打卡系统");
        IO.println("==========================");
        IO.println("1 登录");
        IO.println("2 注册");
        IO.println("0 退出");
        Scanner sc = new Scanner(System.in);
        IO.print("请使用数字选择要进行的操作: ");
        int input = sc.nextInt();
        return HomepageSelections.fromOrdinal(input);
    }

    /**
     * 登录表单。所有角色等级的用户都要在这里登录。
     *
     * @return 用户发起的登录请求。
     */
    public static loginRequest loginForm() {

        IO.println("==========================");
        IO.println("红色文化学习打卡系统");
        IO.println("登录页");
        IO.println("==========================");

        Scanner sc = new Scanner(System.in);
        String username = null;
        String password = null;
        do {
            IO.print("输入用户名: ");
            username = sc.nextLine();
        } while (username.isBlank());

        do {
            IO.print("输入密码: ");
            password = sc.nextLine();
        } while (password.isBlank());

        return new loginRequest(username, password);
    }

    /**
     * 普通用户的注册功能。
     *
     * @return 含有注册信息的dto。
     */
    public static CreateUserRequest registerForm() {
        IO.println("==========================");
        IO.println("红色文化学习打卡系统");
        IO.println("注册页");
        IO.println("==========================");

        Scanner sc = new Scanner(System.in);

        IO.print("请输入用户名: ");
        String username;
        username = sc.nextLine();
        while (username.isBlank()) {
            IO.print("用户名不能为空，请重新输入:");
            username = sc.nextLine();
        }

        String password1;
        String password2;
        String finalPassword = null;

        boolean isDifferentPassword = true;
        while (isDifferentPassword) {
            IO.print("请输入密码: ");
            password1 = sc.nextLine();
            while (password1 == null || password1.isBlank()) {
                IO.print("密码不能为空，请重新输入: ");
                password1 = sc.nextLine();
            }

            IO.print("请确认密码：");
            password2 = sc.nextLine();
            while (password2.isBlank()) {
                IO.print("确认密码不能为空，请重新输入: ");
                password2 = sc.nextLine();
            }

            isDifferentPassword = !(password1.equals(password2));
            if (isDifferentPassword) {
                IO.println("两次输入的密码不一致，请重新输入密码。");
            } else {
                finalPassword = password1;
            }

            // TODO: 还可以加入密码强度的校验规则。
        }

        return new CreateUserRequest(username, finalPassword, 1);
    }
}
