package cn.edu.neusoft.view;

import cn.edu.neusoft.model.User;
import cn.edu.neusoft.model.modPassword;

import java.util.List;
import java.util.Scanner;

/**
 * 仅限管理员使用。这个视图仅限管理员角色操作。
 */
public class ManageUserView {
    /**
     * 向数据库新增用户。
     * @return 返回待增加用户的基础信息，它将由Dao层管理。如果返回了一个空值，表示添加用户的流程失败了。
     */
    public static User addUserView() {
        // 这个视图可以做简单的密码是否一致的
        System.out.println("===========管理员=============");
        System.out.println("===========用户管理===========");
        System.out.println("-----------新增用户-----------");

        Scanner sc = new Scanner(System.in);
        User user = new User();

        System.out.print("请输入用户名: ");
        String username = sc.nextLine();

        System.out.print("请输入密码: ");
        String password_1 = sc.nextLine();

        System.out.print("请确认密码: ");
        String password_2 = sc.nextLine();

        System.out.print("请设置用户角色，分别是管理员（也可以用0表示）和普通用户（也可以用1表示）: ");
        String user_type = sc.nextLine();

        user.setUsername(username);
        if (password_1.equals(password_2)) {
            user.setPassword(password_1);
        } else {
            user.clear();
            return user;
        }

        if (user_type.equals("1") || user_type.equals("普通用户")) {
            user.setRole(1);
        } else if (user_type.equals("0") || user_type.equals("管理员")) {
            user.setRole(0);
        } else {
            user.setRole(1);
        }

        return user;
    }

    /**
     * 用于显示所有用户信息。
     * @param users 被显示的用户数据。
     */
    public static void showAllUsersInfo(List<User> users) {
        System.out.println("==============管理员================");
        System.out.println("==============用户管理==============");
        System.out.println("--------------显示用户--------------");

        for (int i = 0;i < users.size();i++) {
            System.out.printf("第%d个用户: {\n", i + 1);
            System.out.println("用户ID: "+ users.get(i).getUser_id());
            System.out.println("用户名: "+ users.get(i).getUsername());
            System.out.println("用户密码: "+ users.get(i).getPassword());
            System.out.println("用户角色: " + users.get(i).getRole());
            System.out.println("用户学习积分: "+ users.get(i).getStudy_points());
            System.out.println("}");
        }
    }

    //<editor-fold desc="查寻用户">
    /**
     * 根据用户名查找用户。
     * @return  待查找的用户的用户名。
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
