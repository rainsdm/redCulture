package cn.edu.neusoft.view;

import cn.edu.neusoft.model.User;
import cn.edu.neusoft.model.modPassword;

import java.util.List;
import java.util.Scanner;

public class ManageUserView {
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

    public static User addUserView() {
        System.out.println("===========管理员=============");
        System.out.println("===========用户管理===========");
        System.out.println("-----------新增用户-----------");

        Scanner sc = new Scanner(System.in);
        User user = new User();

        System.out.print("请输入用户名：");

    }
}
