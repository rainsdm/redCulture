package cn.edu.neusoft.view;

import java.util.List;
import java.util.Scanner;

import cn.edu.neusoft.model.Records;

public class ManageRecordView {

	/**
	 * 综合管理当前学习记录的提示信息。
	 *
	 * @return 用户选择的菜单。
	 */
	public static int manageRecordView() {
		System.out.println("==============普通用户=================");
		System.out.println("=============显示学习记录===============");

		System.out.println("1. 查找学习记录");
		System.out.println("2. 删除学习记录");
		System.out.println("0. 返回上一级");

		Scanner sc = new Scanner(System.in);
		System.out.print("请选择接下来的操作: ");

		int choice = sc.nextInt();
		sc.nextLine();

		return choice;
	}

	/**
	 * 由用户选择查询记录的方式。
	 *
	 * @return 用户选择的查询方案
	 */
	public static int selectRecordMethod() {
		System.out.println("==============普通用户=================");
		System.out.println("============学习记录查询方案=============");

		System.out.println("1. 查询所有记录");
		System.out.println("2. 根据景点ID查找学习记录");
		System.out.println("3. 根据学习记录ID查找");
		System.out.println("0. 结束查询");

		Scanner sc = new Scanner(System.in);
		System.out.print("请选择你的查找方式: ");

		int choice = sc.nextInt();
		sc.nextLine();

		return choice;
	}

	/**
	 * 显示单个学习记录的信息。
	 *
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

		for (int i = 0; i < records.size(); i++) {
			Records record = records.get(i);
			System.out.printf("第%d条记录: \n", i + 1);
			showRecordView(record);
		}
	}

	public static int searchRecordByRecordIDView() {
		Scanner sc = new Scanner(System.in);
		System.out.print("请选择要查询的记录的ID: "); // 受限于底层代码设置，现在只能根据景点的ID查找。
		int record_id = sc.nextInt();
		sc.nextLine();

		return record_id;
	}

	public static int searchRecordBySpotIDView() {
		Scanner sc = new Scanner(System.in);
		System.out.print("请选择要查询的景点记录的ID: ");
		int spot_id = sc.nextInt();
		sc.nextLine();

		return spot_id;
	}

	public static int deleteRecordByRecordIDView() {
		Scanner sc = new Scanner(System.in);
		System.out.print("请选择要删除的学习记录ID: ");
		int record_id = sc.nextInt();
		sc.nextLine();

		return record_id;
	}
}
