package cn.edu.neusoft.domain.spot.role.admin.view;

import cn.edu.neusoft.domain.spot.model.Spot;

import java.util.List;
import java.util.Scanner;

public class ManageSpotView {
    /**
     * 管理员管理景点的总页面。
     *
     * @return 用户选择的菜单。
     */
    public static int mainView() {
        IO.println("===============管理员=================");
        IO.println("==============用户管理=================");
        IO.println("1. 添加景点");
        IO.println("2. 删除景点");
        IO.println("3. 进入景点综合查询页面");

        IO.println("0. 退出");

        Scanner sc = new Scanner(System.in);
        int selectedMenu = sc.nextInt();
        sc.nextLine();

        return selectedMenu;
    }

    public static Spot addSpot() {
        IO.println("===============管理员=================");
        IO.println("==============增加景点=================");

        Scanner sc = new Scanner(System.in);
        Spot spot = new Spot();
        IO.print("请输入新景点的名称: ");
        spot.setSpot_name(sc.nextLine());
        IO.print("请输入新景点的所在城市: ");
        spot.setLocation(sc.nextLine());
        IO.print("请输入新景点的历史信息: ");
        spot.setHistory(sc.nextLine());

        return spot;
    }

    /**
     * 管理员删除景点的界面。<br>
     * 它只负责接收信息，不与数据库交互。
     *
     * @return 准确的景点名称。
     */
    public static String deleteSpot() {
        IO.println("===============管理员=================");
        IO.println("==============删除景点=================");

        Scanner sc = new Scanner(System.in);
        IO.println("为了保证能够查找到，你必须输入完整的景点名称。");
        IO.print("请在此处输入待删除的景点的名称: ");

        return sc.nextLine();
    }

    /**
     * 管理员查询用户总页面。
     *
     * @return 具体查询菜单。
     */
    public static int searchView() {
        IO.println("=================管理员=================");
        IO.println("=============景点查询综合管理==============");
        IO.println("1.查询所有景点");
        IO.println("2.按精确的景点名查找");
        IO.println("3.按ID查找景点");

        IO.println("0.返回");

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
        IO.println("=================管理员=================");
        IO.println("--------------显示所有景点--------------");

        for (int i = 0; i < spots.size(); i++) {
            System.out.printf("第%d个景点: {\n", i + 1);
            IO.println("景点名称: " + spots.get(i).getSpot_name());
            IO.println("景点位置: " + spots.get(i).getLocation());
            IO.println("景点的历史信息: " + spots.get(i).getHistory());
            IO.println("}");
            IO.println();
        }
    }

    public static void showSpotInfo(Spot spot) {
        IO.println("=================管理员=================");
        IO.println("--------------显示当前景点--------------");

        IO.println("景点名称: " + spot.getSpot_name());
        IO.println("景点位置: " + spot.getLocation());
        IO.println("景点的历史信息: " + spot.getHistory());
    }

    public static String searchByNameView() {
        IO.print("请输入待查找的景点名: ");
        Scanner sc = new Scanner(System.in);

        return sc.nextLine();
    }

    public static int searchByIdView() {
        IO.print("请输入待查找的景点ID: ");
        Scanner sc = new Scanner(System.in);
        int spotId = sc.nextInt();
        sc.nextLine();

        return spotId;
    }
}
