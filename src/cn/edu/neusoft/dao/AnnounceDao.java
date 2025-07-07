package cn.edu.neusoft.dao;

import cn.edu.neusoft.model.Announcement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AnnounceDao {
    public boolean addAnnouncement(Announcement announcement) {
        Connection conn = BaseDao.getConnection();
        PreparedStatement pre_stmt = null;
        String sql = "insert into announcements(title, content, post_time, comment) values(?,?,?,?)";
        int result = 0;
        try {
            pre_stmt = conn.prepareStatement(sql);
            pre_stmt.setString(1, announcement.getAnnouncementTitle());
            pre_stmt.setString(2, announcement.getAnnouncementContent());
            pre_stmt.setString(3, announcement.getAnnouncementPostTime());
            pre_stmt.setString(4, announcement.getAnnouncementComment());

            result = pre_stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDao.closeStatement(pre_stmt);
            BaseDao.closeConnection(conn);
        }

        return result > 0;
    }
}
