package cn.edu.neusoft.test;

import cn.edu.neusoft.dao.UserDao;
import cn.edu.neusoft.model.User;

import java.util.Scanner;

public class UserDaoTest {
    public static void main(String[] args) {
        User usr = new User();
        System.out.print("请输入要查询的用户信息：");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        UserDao ud = new UserDao();
        usr = ud.findByUsername(username);
        if (usr.getUserID() == null) {
            System.out.println("指定的用户不存在");
        } else {
            System.out.println(usr);
        }
    }
}
