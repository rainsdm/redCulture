package cn.edu.neusoft.view;

import cn.edu.neusoft.model.Spot;

import java.util.List;
import java.util.Scanner;

/**
 * 普通用户景点打卡学习页面
 */
public class SpotLearnView {
    /**
     * 浏览所有景点，然后让用户切换页面，或者查询特定景点。
     * @param spots 完整的景点列表信息。
     * @return 用户选择的下一步操作。
     */
    public static int showSpotsView(List<Spot> spots) {
        System.out.println("==============普通用户================");
        System.out.println("==============浏览景点================");

        for (Spot spot : spots) {
            System.out.print("景点ID: " +  spot.getSpot_id() + ", ");
            System.out.println("景点名称: " +  spot.getSpot_name());

        }

        System.out.println("--------------------------------------");
        System.out.println("1. 上一页");
        System.out.println("2. 下一页");
        System.out.println("3. 学习打卡"); // 查看某一个景点的详细信息
        System.out.println("0. 返回");

        Scanner sc = new Scanner(System.in);

        return sc.nextInt();
    }

    /**
     * 选择要查看的景点ID。
     * @return 用户选择的景点ID信息。
     */
    public static int chooseSpot() {
        System.out.println("==============普通用户================");
        System.out.println("==============浏览景点================");

        System.out.println("请输入要查看的景点ID: ");
        Scanner sc = new Scanner(System.in);

        return sc.nextInt();
    }

    /**
     * 显示景点详情，并且显示写笔记和退出信息的菜单。
     *
     * @param spot 从数据库中获取到的景点信息。
     * @return 用户选择的菜单编号。
     */
    public static int showSpotDetail (Spot spot) {
        System.out.println("==============普通用户================");
        System.out.println("=============查看景点详情===============");

        System.out.println("景点id："+spot.getSpot_id());
        System.out.println("景点名："+spot.getSpot_name());
        System.out.println("地理位置："+spot.getLocation());
        System.out.println("历史："+spot.getHistory());
        System.out.println("--------------------------------------");

        System.out.println("1. 写笔记");
        System.out.println("0. 退出");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
}
