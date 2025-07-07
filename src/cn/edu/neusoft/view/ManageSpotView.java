package cn.edu.neusoft.view;

import cn.edu.neusoft.model.Spot;
import cn.edu.neusoft.model.User;

import java.util.List;
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
        System.out.println("1. 添加景点");
        System.out.println("2. 删除景点");
        System.out.println("3. 进入景点综合查询页面");

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

    /**
     * 管理员查询用户总页面。
     * @return 具体查询菜单。
     */
    public static int searchView() {
        System.out.println("=================管理员=================");
        System.out.println("=============景点查询综合管理==============");
        System.out.println("1.查询所有景点");
        System.out.println("2.按精确的景点名查找");
        System.out.println("3.按ID查找景点");

        System.out.println("0.返回");

        Scanner sc = new Scanner(System.in);
        int menu = sc.nextInt();
        if (menu > 3 || menu < 0) {
            menu = 0;
        }

        return menu;
    }

        /**
     * 用于显示所有用户信息。
     *
     * @param spots 被显示的用户数据。
     */
    public static void showAllSpotsInfo(List<Spot> spots) {
        System.out.println("=================管理员=================");
        System.out.println("--------------显示所有景点--------------");

        for (int i = 0; i < spots.size(); i++) {
            System.out.printf("第%d个景点: {\n", i + 1);
            System.out.println("景点名称: " + spots.get(i).getSpot_name());
            System.out.println("景点位置: " + spots.get(i).getLocation());
            System.out.println("景点的历史信息: " + spots.get(i).getHistory());
            System.out.println("}");
            System.out.println();
        }
    }

    public static void showSpotInfo(Spot spot) {
        System.out.println("=================管理员=================");
        System.out.println("--------------显示当前景点--------------");

        System.out.println("景点名称: " + spot.getSpot_name());
        System.out.println("景点位置: " + spot.getLocation());
        System.out.println("景点的历史信息: " + spot.getHistory());
    }

    public static String searchByNameView() {
        System.out.print("请输入待查找的景点名: ");
        Scanner sc = new Scanner(System.in);

        return sc.nextLine();
    }

    public static int searchByIdView() {
        System.out.print("请输入待查找的景点ID: ");
        Scanner sc = new Scanner(System.in);
        int spotId = sc.nextInt();
        sc.nextLine();

        return spotId;
    }
}
