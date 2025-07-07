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
        System.out.println("==============增加景点=================");

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

    /**
     * 管理员删除景点的界面。<br>它只负责接收信息，不与数据库交互。
     * @return 准确的景点名称。
     */
    public static String deleteSpot() {
        System.out.println("===============管理员=================");
        System.out.println("==============删除景点=================");

        Scanner sc = new Scanner(System.in);
        System.out.println("为了保证能够查找到，你必须输入完整的景点名称。");
        System.out.print("请在此处输入待删除的景点的名称: ");

        return sc.nextLine();
    }
}
