package cn.edu.neusoft.domain.spot.view;

import cn.edu.neusoft.model.Records;
import cn.edu.neusoft.domain.spot.model.Spot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 普通用户景点打卡学习页面
 */
public class SpotLearnView {
    /**
     * 浏览所有景点，然后让用户切换页面，或者查询特定景点。
     *
     * @param spots 完整的景点列表信息。
     * @return 用户选择的下一步操作。
     */
    public static int showSpotsView(List<Spot> spots) {
        IO.println("==============普通用户================");
        IO.println("==============浏览景点================");

        for (Spot spot : spots) {
            IO.print("景点ID: " + spot.getSpot_id() + ", ");
            IO.println("景点名称: " + spot.getSpot_name());

        }

        IO.println("--------------------------------------");
        IO.println("1. 上一页");
        IO.println("2. 下一页");
        IO.println("3. 学习打卡"); // 查看某一个景点的详细信息
        IO.println("0. 返回");

        Scanner sc = new Scanner(System.in);

        return sc.nextInt();
    }

    /**
     * 选择要查看的景点ID。
     *
     * @return 用户选择的景点ID信息。
     */
    public static int chooseSpot() {
        IO.println("==============普通用户================");
        IO.println("==============浏览景点================");

        IO.println("请输入要查看的景点ID: ");
        Scanner sc = new Scanner(System.in);

        return sc.nextInt();
    }

    /**
     * 显示景点详情，并且显示写笔记和退出信息的菜单。
     *
     * @param spot 从数据库中获取到的景点信息。
     * @return 用户选择的菜单编号。
     */
    public static int showSpotDetail(Spot spot) {
        IO.println("==============普通用户================");
        IO.println("=============查看景点详情===============");

        IO.println("景点id：" + spot.getSpot_id());
        IO.println("景点名：" + spot.getSpot_name());
        IO.println("地理位置：" + spot.getLocation());
        IO.println("历史：" + spot.getHistory());
        IO.println("--------------------------------------");

        IO.println("1. 写笔记");
        IO.println("0. 退出");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public static String writeNoteView() {
        IO.println("==============普通用户================");
        IO.println("===============写笔记=================");

        IO.println("请发表你的学习笔记: ");
        Scanner sc = new Scanner(System.in);

        return sc.next();
    }

    /**
     * 根据时间搜索记录的视图。
     *
     * @return 包含了开始时间和结束时间的列表。
     */
    public static List<String> searchByTimeView() {
        IO.println("===================普通用户=====================");
        IO.println("===============根据日期查询学习记录=================");

        Scanner sc = new Scanner(System.in);
        List<String> timeList = new ArrayList<>();

        IO.print("请输入开始时间: ");
        String startTime = sc.nextLine();
        IO.print("请输入结束时间: ");
        String endTime = sc.nextLine();

        timeList.add(startTime);
        timeList.add(endTime);

        return timeList;
    }

    /**
     * 显示查找到的学习记录。
     *
     * @param recordsList 待显示的学习记录信息。
     */
    public static void showLearnRecord(List<Records> recordsList) {
        IO.println("==================普通用户====================");
        IO.println("===============查找到的学习记录=================");

        if (recordsList.isEmpty()) {
            IO.println("没有找到学习记录！");
        } else {
            for (Records record : recordsList) {
                IO.println(record);
            }
        }
    }

    public static void showPopularSpot(List<Spot> spots) {
        IO.println("========显示热门景点排序======");
        for (Spot spot : spots) {
            IO.println("景点id:" + spot.getSpot_id());
            IO.println("景点名称:" + spot.getSpot_name());
        }

        IO.println("---------------------------");
    }
}
