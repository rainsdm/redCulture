package cn.edu.neusoft.view;

import cn.edu.neusoft.model.Announcement;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class AnnouncementManageView {
    public static Announcement addAnnouncement() {
        System.out.println("===============管理员=================");
        System.out.println("==============公告管理=================");
        System.out.println(" -------------添加公告------------- ");

        Announcement announcement = new Announcement();
        Scanner sc = new Scanner(System.in);

        System.out.print("请输入公告的标题: ");
        announcement.setAnnouncementTitle(sc.nextLine());

        System.out.println("请在下方输入公告正文: ");
        announcement.setAnnouncementContent(sc.nextLine());

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(timestamp);
        announcement.setAnnouncementPostTime(date);

        System.out.print("是否需要输入注释?(y/N) ");
        String choice = sc.nextLine();
        if (choice.equals("y")) {
            System.out.print("请输入注释: ");
            announcement.setAnnouncementComment(sc.nextLine());
        } else {
            announcement.setAnnouncementComment("");
        }

        return announcement;
    }

    public static int deleteAnnouncement() {
        Scanner sc = new Scanner(System.in);
        System.out.print("请选择要删除的公告的ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        return id;
    }

    public static Announcement updateAnnouncement() {
        Announcement announcement = new Announcement();

        Scanner sc = new Scanner(System.in);
        System.out.print("请选择要修改的公告id。如果不修改，请留空: ");
        String st_id = sc.nextLine();
        int int_id;
        try {
            int_id = Integer.parseInt(st_id);
        } catch (NumberFormatException e) {
            int_id = -1;
        }
        announcement.setAnnouncementID(int_id);

        System.out.print("请输入新的标题。如果不修改，请留空: ");
        String title = sc.nextLine();
        announcement.setAnnouncementTitle(title);

        System.out.println("请在下方输入要修改的正文内容。如果不修改，请留空: ");
        String content = sc.nextLine();
        announcement.setAnnouncementContent(content);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(timestamp);
        announcement.setAnnouncementPostTime(date);

        System.out.println("请在下方输入要修改的备注内容。如果不修改，请留空: ");
        String comment = sc.nextLine();
        announcement.setAnnouncementComment(comment);

        return announcement;
    }
}
