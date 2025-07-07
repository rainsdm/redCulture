package cn.edu.neusoft.view;

import java.util.Scanner;

public class ManagerSpotView {
    /**
     * 管理景点的汇总页面。
     * @return 用户选择的下一个页面编号。
     */
    public static int mainSpotManagePage() {
        System.out.println("===============管理员===============");
        System.out.println("==============景点管理==============");
        System.out.println("1. 添加景点");
        System.out.println("2. 删除景点");
        System.out.println("3. 进入景点综合查询页面");
        System.out.println("4. 修改景点");

        System.out.println("0. 退出");

        Scanner sc = new Scanner(System.in);
        int selectedMenu = sc.nextInt();
        sc.nextLine();

        return selectedMenu;
    }
}
