package cn.edu.neusoft.controller;

import java.util.List;

import cn.edu.neusoft.dao.AnnounceDao;
import cn.edu.neusoft.model.Announcement;
import cn.edu.neusoft.view.AnnouncementManageView;

public class ManageAnnouncementC {
	private final AnnounceDao ad = new AnnounceDao();

	// <editor-fold desc="增、删、改">
	public void addAnnouncement(Announcement announcement) {
		if (announcement != null && ad.addAnnouncement(announcement)) {
			IO.println("公告增加成功! ");
		} else {
			IO.println("公告增加失败! ");
		}
	}

	public void deleteAnnouncement() {
		int anno_id_to_delete = AnnouncementManageView.deleteAnnouncement();
		Announcement announcement = ad.searchAnnouncementByAnnouncementID(anno_id_to_delete);
		if (announcement != null && announcement.getAnnouncementID() == anno_id_to_delete) {
			if (ad.deleteAnnouncement(announcement.getAnnouncementID())) {
				IO.println("公告删除成功! ");
			} else {
				IO.println("公告删除失败! ");
			}
		} else {
			IO.println("没有找到要删除的公告。");
		}
	}

	public void updateAnnouncement() {
		Announcement new_anno = AnnouncementManageView.updateAnnouncement(); // 从视图获取的新的信息。
		if (new_anno.getAnnouncementID() == -1) {
			IO.println("没有选择要修改的公告，不需要进行更新。");
			return;
		}
		Announcement old_announcement = ad.searchAnnouncementByAnnouncementID(new_anno.getAnnouncementID()); // 从数据库查到的原始信息。
		Announcement toUpdateAnnouncement = new Announcement();
		boolean hasChanged = false; // 判断是否发生了变化。有变化时，才需要发生改变。

		toUpdateAnnouncement.setAnnouncementID(new_anno.getAnnouncementID());

		if (!new_anno.getAnnouncementTitle().isEmpty()) {
			// 已修改的操作
			toUpdateAnnouncement.setAnnouncementTitle(new_anno.getAnnouncementTitle());
			hasChanged = true;
		} else {
			// 未修改的操作
			toUpdateAnnouncement.setAnnouncementTitle(old_announcement.getAnnouncementTitle());
		}

		if (!new_anno.getAnnouncementContent().isEmpty()) {
			// 已修改的操作
			toUpdateAnnouncement.setAnnouncementContent(new_anno.getAnnouncementContent());
			hasChanged = true;
		} else {
			// 未修改的操作
			toUpdateAnnouncement.setAnnouncementContent(old_announcement.getAnnouncementContent());
		}

		if (!new_anno.getAnnouncementComment().isEmpty()) {
			// 已修改的操作
			toUpdateAnnouncement.setAnnouncementComment(new_anno.getAnnouncementComment());
			hasChanged = true;
		} else {
			// 未修改的操作
			toUpdateAnnouncement.setAnnouncementComment(old_announcement.getAnnouncementComment());
		}

		toUpdateAnnouncement.setAnnouncementPostTime(old_announcement.getAnnouncementPostTime()); // 数据库目前只能存储初始的发布时间。暂时不打算更改。

		if (hasChanged) {
			if (ad.updateAnnouncement(toUpdateAnnouncement)) {
				IO.println("公告修改成功! ");
			} else {
				IO.println("公告修改失败! ");
			}
		} else {
			IO.println("不需要修改公告。");
		}
	}
	// </editor-fold>

	// <editor-fold desc="查找公告">
	public void searchAnnouncement() {
		boolean searchOrNot = true;
		while (searchOrNot) {
			int method = AnnouncementManageView.selectAnnouncementMethod();
			switch (method) {
			case 0: // 结束查询
				searchOrNot = false;
				break;
			case 1: // 显示所有公告
				searchAllAnnouncementC(); // 普通用户只能查询自己的学习记录。
				break;
			case 2: // 根据公告ID查找
				searchRecordByAnnouncementIDC();
				break;
			case 3: // 根据公告标题
				searchRecordByAnnouncementTitleC();
				break;
			}
		}

	}

	public void searchAllAnnouncementC() {
		List<Announcement> announcements = ad.searchAllAnnouncement();
		for (Announcement announcement : announcements) {
			IO.println(announcement);
			IO.println();
		}
	}

	public void searchRecordByAnnouncementIDC() {
		int id = AnnouncementManageView.searchAnnouncementByIDView();
		Announcement announcement = ad.searchAnnouncementByAnnouncementID(id);
		if (announcement.getAnnouncementID() == id) {
			IO.println(announcement);
		} else {
			IO.println("没有找到对应的公告! ");
		}
	}

	public void searchRecordByAnnouncementTitleC() {
		String title = AnnouncementManageView.searchAnnouncementByTitleView();
		Announcement announcement = ad.searchAnnouncementByTitle(title);
		if (announcement.getAnnouncementTitle() != null && announcement.getAnnouncementTitle().equals(title)) {
			IO.println(announcement);
		} else {
			IO.println("没有找到对应的公告! ");
		}
	}
	// </editor-fold>
}
