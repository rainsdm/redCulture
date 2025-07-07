package cn.edu.neusoft.view;

import cn.edu.neusoft.model.Spot;

import java.util.Scanner;

public class ManageSpotView {
    /**
     * 管理员管理景点的总页面。
     *
     * @return 用户选择的菜单。
     */
    public static int mainView() {
        System.out.println("===============管理员=================");
        System.out.println("==============用户管理=================");
        System.out.println("1. 进入景点综合查询页面");
        System.out.println("2. 删除景点");
        System.out.println("3. 添加景点");

        System.out.println("0. 退出");

        Scanner sc = new Scanner(System.in);
        int selectedMenu = sc.nextInt();
        sc.nextLine();

        return selectedMenu;
    }

    public static Spot addSpot() {
        System.out.println("===============管理员=================");
        System.out.println("==============增加管理=================");

        Scanner sc = new Scanner(System.in);
        Spot spot = new Spot();
        System.out.print("请输入新景点的名称: ");
        spot.setSpot_name(sc.nextLine());
        System.out.print("请输入新景点的所在城市: ");
        spot.setLocation(sc.nextLine());
        System.out.print("请输入新景点的历史信息: ");
        spot.setHistory(sc.nextLine());

        return spot;
    }
}
