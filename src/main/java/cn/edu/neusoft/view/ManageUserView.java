package cn.edu.neusoft.view;

import cn.edu.neusoft.dto.user.auth.request.CreateUserRequest;
import cn.edu.neusoft.model.User;

import java.util.List;
import java.util.Scanner;

/**
 * 仅限管理员使用。这个视图仅限管理员角色操作。
 */
public class ManageUserView {

    /**
     * 管理员管理用户的总页面。
     *
     * @return 用户选择的菜单。
     */
    public static int mainView() {
        System.out.println("===============管理员=================");
        System.out.println("==============用户管理=================");
        System.out.println("1. 进入用户综合查询页面");
        System.out.println("2. 删除用户");
        System.out.println("3. 添加用户");

        System.out.println("0. 退出");

        Scanner sc = new Scanner(System.in);
        int selectedMenu = sc.nextInt();
        sc.nextLine();

        return selectedMenu;
    }

    /**
     * 管理员查询用户总页面。
     *
     * @return 具体查询菜单。
     */
    public static int searchView() {
        System.out.println("================管理员==================");
        System.out.println("=============用户综合管理==============");
        System.out.println("1.查询所有用户");
        System.out.println("2.按姓名查找用户");
        System.out.println("3.按ID查找用户");
        System.out.println("4.按角色查找用户");
        System.out.println("0.返回");

        Scanner sc = new Scanner(System.in);
        int menu = sc.nextInt();
        if (menu > 4 || menu < 0) {
            menu = 0;
        }

        return menu;
    }

    //<editor-fold desc="增加/删除用户">

    /**
     * 向数据库新增用户。
     *
     * @return 返回待增加用户的基础信息，它将由Dao层管理。如果返回了一个空值，表示添加用户的流程失败了。
     */
    public static CreateUserRequest addUserView() {
        // 这个视图可以做简单的密码是否一致的
        System.out.println("===========管理员=============");
        System.out.println("===========用户管理===========");
        System.out.println("-----------新增用户-----------");

        Scanner sc = new Scanner(System.in);

        System.out.print("请输入用户名: ");
        String username = sc.nextLine();
        while (username.isBlank()) {
            System.out.print("用户名不能为空，请重新输入:");
            username = sc.nextLine();
        }

        boolean isDifferentPassword = true;

        String password_1 = "";
        String password_2 = "";
        String finalPassword = null;

        while (isDifferentPassword) {
            System.out.print("请输入密码: ");
            password_1 = sc.nextLine();
            while (password_1 == null || password_1.isBlank()) {
                System.out.print("密码不能为空，请重新输入: ");
                password_1 = sc.nextLine();
            }

            System.out.print("请确认密码: ");
            password_2 = sc.nextLine();
            while (password_2.isBlank()) {
                System.out.print("确认密码不能为空，请重新输入: ");
                password_2 = sc.nextLine();
            }

            isDifferentPassword = !(password_1.equals(password_2));
            if (isDifferentPassword) {
                System.out.println("两次输入的密码不一致，请重新输入密码。");
            } else {
                finalPassword = password_1;
            }
        }

        System.out.print("请设置用户角色，分别是管理员（也可以用0表示）和普通用户（也可以用1表示）: ");
        String user_type = sc.nextLine();

        int role = switch (user_type) {
            case "1", "普通用户" -> 1;
            case "0", "管理员" -> 0;
            default -> 1;
        };

        CreateUserRequest newUser = new CreateUserRequest(username, finalPassword, role);

        return newUser;
    }

    /**
     * 根据用户名删除数据库中的指定用户。
     *
     * @return 被删除的用户的名称。
     */
    public static String deleteUserView() {
        System.out.println("===========管理员=============");
        System.out.println("===========用户管理===========");
        System.out.println("-----------删除用户-----------");

        Scanner sc = new Scanner(System.in);

        System.out.print("请输入要删除的用户名: ");
        String username = sc.nextLine();

        if (!username.isEmpty()) {
            return username;
        } else {
            return "";
        }
    }
    //</editor-fold>

    /**
     * 用于显示所有用户信息。
     *
     * @param users 被显示的用户数据。
     */
    public static void showAllUsersInfo(List<User> users) {
        System.out.println("==============管理员================");
        System.out.println("==============用户管理==============");
        System.out.println("--------------显示用户--------------");

        for (int i = 0; i < users.size(); i++) {
            System.out.printf("第%d个用户: {\n", i + 1);
            System.out.println("用户ID: " + users.get(i).getUserId());
            System.out.println("用户名: " + users.get(i).getUsername());
            System.out.println("用户密码: " + users.get(i).getPassword());
            System.out.println("用户角色: " + users.get(i).roleToString());
            System.out.println("用户学习积分: " + users.get(i).getStudyPoints());
            System.out.println("}");
        }
    }

    //<editor-fold desc="查寻用户">

    /**
     * 根据用户名查找用户。
     *
     * @return 待查找的用户的用户名。
     */
    public static String searchByNameView() {
        System.out.print("请输入待查找的用户名: ");
        Scanner sc = new Scanner(System.in);

        return sc.nextLine();
    }

    public static String searchByIdView() {
        System.out.print("请输入待查找的用户ID: ");
        Scanner sc = new Scanner(System.in);

        return sc.nextLine();
    }

    public static String searchByRole() {
        System.out.println("请输入待查找的角色名称: ");
        Scanner sc = new Scanner(System.in);

        return sc.nextLine();
    }
    //</editor-fold>


}
