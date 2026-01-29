package cn.edu.neusoft.view;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import cn.edu.neusoft.model.Announcement;

public class AnnouncementManageView {
	public static int adminManagePage() {
		Scanner sc = new Scanner(System.in);
		IO.println("===============管理员=================");
		IO.println("==============公告管理=================");
		IO.println("1. 添加公告");
		IO.println("2. 删除公告");
		IO.println("3. 修改公告");
		IO.println("4. 查询公告");

		IO.println("0. 退出登录");

		int menu = sc.nextInt();
		sc.nextLine();

		return menu;
	}

	public static Announcement addAnnouncement() {
		IO.println("===============管理员=================");
		IO.println("==============公告管理=================");
		IO.println(" -------------添加公告------------- ");

		Announcement announcement = new Announcement();
		Scanner sc = new Scanner(System.in);

		IO.print("请输入公告的标题: ");
		announcement.setAnnouncementTitle(sc.nextLine());

		IO.println("请在下方输入公告正文: ");
		announcement.setAnnouncementContent(sc.nextLine());

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(timestamp);
		announcement.setAnnouncementPostTime(date);

		IO.print("是否需要输入注释?(y/N) ");
		String choice = sc.nextLine();
		if (choice.equals("y")) {
			IO.print("请输入注释: ");
			announcement.setAnnouncementComment(sc.nextLine());
		} else {
			announcement.setAnnouncementComment("");
		}

		return announcement;
	}

	public static int deleteAnnouncement() {
		Scanner sc = new Scanner(System.in);
		IO.print("请选择要删除的公告的ID: ");
		int id = sc.nextInt();
		sc.nextLine();

		return id;
	}

	public static Announcement updateAnnouncement() {
		Announcement announcement = new Announcement();

		Scanner sc = new Scanner(System.in);
		IO.print("请选择要修改的公告id。如果不修改，请留空: ");
		String st_id = sc.nextLine();
		int int_id;
		try {
			int_id = Integer.parseInt(st_id);
		} catch (NumberFormatException _) {
			int_id = -1;
		}
		announcement.setAnnouncementID(int_id);

		IO.print("请输入新的标题。如果不修改，请留空: ");
		String title = sc.nextLine();
		announcement.setAnnouncementTitle(title);

		IO.println("请在下方输入要修改的正文内容。如果不修改，请留空: ");
		String content = sc.nextLine();
		announcement.setAnnouncementContent(content);

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(timestamp);
		announcement.setAnnouncementPostTime(date);

		IO.println("请在下方输入要修改的备注内容。如果不修改，请留空: ");
		String comment = sc.nextLine();
		announcement.setAnnouncementComment(comment);

		return announcement;
	}

	/**
	 * 由用户选择查询公告的方式。
	 *
	 * @return 用户选择的查询方案
	 */
	public static int selectAnnouncementMethod() {
		IO.println("============公告查询方案=============");

		IO.println("1. 查询所有公告");
		IO.println("2. 根据公告ID查找");
		IO.println("3. 根据公告标题查找");

		IO.println("0. 结束查询");

		Scanner sc = new Scanner(System.in);
		IO.print("请选择你的查找方式: ");

		int choice = sc.nextInt();
		sc.nextLine();

		return choice;
	}

	public static int searchAnnouncementByIDView() {
		Scanner sc = new Scanner(System.in);
		IO.print("请选择要查询的公告的ID: ");
		int anno_id = sc.nextInt();
		sc.nextLine();

		return anno_id;
	}

	public static String searchAnnouncementByTitleView() {
		Scanner sc = new Scanner(System.in);
		IO.print("请输入要搜索的公告标题: ");

		return sc.nextLine();
	}
}
