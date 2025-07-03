package cn.edu.neusoft.view;

import cn.edu.neusoft.model.Records;

import java.util.List;
import java.util.Scanner;

public class ManageRecordView {
    //<editor-fold desc="学习记录管理">
    /**
     * 显示单个学习记录的信息。
     * @param record 单个被显示的学习记录。
     */
    public static void showRecordView(Records record) {
        if (record != null && record.getUser_id() != null) {
            System.out.println("笔记ID: " + record.getRecord_id());
            System.out.println("用户ID: " + record.getUser_id());
            System.out.println("景点ID: " + record.getSpot_id());
            System.out.println("产生时间: " + record.getProduce_time());
            System.out.println("笔记内容: " + record.getLearn_note());
            System.out.println("---------------------------------");
        } else {
            System.out.println("没找到对应的记录。");
        }
    }

    public static void showAllRecordView(List<Records> records) {
        System.out.println("===============管理员===============");
        System.out.println("===========显示所有学习记录===========");

        for (int i = 0;i < records.size();i++) {
            Records record = records.get(i);
            System.out.printf("第%d条记录: \n",  i + 1);
            showRecordView(record);
        }
    }


    public static int searchRecordByIDView() {
        Scanner sc = new Scanner(System.in);
        System.out.print("请选择要查询的记录的ID: ");
        int record_id = sc.nextInt();
        sc.nextLine();

        return record_id;
    }
    //</editor-fold>
}
