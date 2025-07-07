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
}
