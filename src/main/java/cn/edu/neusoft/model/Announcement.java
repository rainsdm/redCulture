package cn.edu.neusoft.model;

public class Announcement {
	private int announcementID;
	private String announcementTitle;
	private String announcementContent;
	private String announcementPostTime;
	private String announcementComment;

	public Announcement() {
	}

	public Announcement(int announcementID, String announcementTitle, String announcementContent,
			String announcementPostTime, String announcementComment) {
		this.announcementID = announcementID;
		this.announcementTitle = announcementTitle;
		this.announcementContent = announcementContent;
		this.announcementPostTime = announcementPostTime;
		this.announcementComment = announcementComment;
	}

	public int getAnnouncementID() {
		return announcementID;
	}

	public void setAnnouncementID(int announcementID) {
		this.announcementID = announcementID;
	}

	public String getAnnouncementTitle() {
		return announcementTitle;
	}

	public void setAnnouncementTitle(String announcementTitle) {
		this.announcementTitle = announcementTitle;
	}

	public String getAnnouncementContent() {
		return announcementContent;
	}

	public void setAnnouncementContent(String announcementContent) {
		this.announcementContent = announcementContent;
	}

	public String getAnnouncementPostTime() {
		return announcementPostTime;
	}

	public void setAnnouncementPostTime(String announcementPostTime) {
		this.announcementPostTime = announcementPostTime;
	}

	public String getAnnouncementComment() {
		return announcementComment;
	}

	public void setAnnouncementComment(String announcementComment) {
		this.announcementComment = announcementComment;
	}

	@Override
	public String toString() {
		String toString = announcementTitle + "\n" + "通知正文如下: \n" + announcementContent;
		return toString;
	}
}
