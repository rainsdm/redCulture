package cn.edu.neusoft.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Announcement {
    private int announcementID;
    private String announcementTitle;
    private String announcementContent;
    private String announcementPostTime;
    private String announcementComment;

    @Override
    public String toString() {
        String toString = announcementTitle + "\n" + "通知正文如下: \n" + announcementContent;
        return toString;
    }
}
