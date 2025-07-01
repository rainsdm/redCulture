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
}
