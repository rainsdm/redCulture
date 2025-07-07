package cn.edu.neusoft.controller;

import cn.edu.neusoft.dao.AnnounceDao;
import cn.edu.neusoft.model.Announcement;
import cn.edu.neusoft.view.AnnouncementManageView;

public class ManageAnnouncementC {
    AnnounceDao ad = new AnnounceDao();

    public void addAnnouncement(Announcement announcement) {
        if (announcement != null && ad.addAnnouncement(announcement)) {
            System.out.println("公告增加成功! ");
        } else {
            System.out.println("公告增加失败! ");
        }
    }

    public void deleteAnnouncement() {
        int anno_id_to_delete = AnnouncementManageView.deleteAnnouncement();
        Announcement announcement = ad.searchAnnouncementByRecordID(anno_id_to_delete);
        if (announcement != null && announcement.getAnnouncementID() == anno_id_to_delete) {
            if (ad.deleteAnnouncement(announcement.getAnnouncementID())) {
            System.out.println("公告删除成功! ");
            } else {
            System.out.println("公告删除失败! ");
            }
        } else {
            System.out.println("没有找到要删除的公告。");
        }
    }
}
