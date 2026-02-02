package cn.edu.neusoft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.neusoft.model.Announcement;

public class AnnounceDao {
	// <editor-fold desc="增、改、删">
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

	public boolean deleteAnnouncement(int anno_id) {
		int num = 0;
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;

		String sql = null;

		try {
			sql = "delete from announcements where anno_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, anno_id);
			num = ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			BaseDao.closeStatement(ps);
			BaseDao.closeConnection(conn);
		}
		return num > 0;
	}

	public boolean updateAnnouncement(Announcement announcement) {
		int num = 0;
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;

		String sql = null;

		try {
			sql = "update announcements set title=?, content=?, post_time=?, comment=? where anno_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, announcement.getAnnouncementTitle());
			ps.setString(2, announcement.getAnnouncementContent());
			ps.setString(3, announcement.getAnnouncementPostTime());
			ps.setString(4, announcement.getAnnouncementComment());
			ps.setInt(5, announcement.getAnnouncementID());
			num = ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			BaseDao.closeStatement(ps);
			BaseDao.closeConnection(conn);
		}
		return num > 0;
	}
	// </editor-fold>

	// <editor-fold desc="查">
	public List<Announcement> searchAllAnnouncement() {
		Connection conn = BaseDao.getConnection();
		List<Announcement> announcements = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "select * from announcements order by post_time desc";
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				Announcement announcement = new Announcement();
				announcement.setAnnouncementID(rs.getInt("id"));
				announcement.setAnnouncementTitle(rs.getString("title"));
				announcement.setAnnouncementContent(rs.getString("content"));
				announcement.setAnnouncementPostTime(rs.getString("post_time"));
				announcement.setAnnouncementComment(rs.getString("remarks"));

				announcements.add(announcement);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			BaseDao.closeResultSet(rs);
			BaseDao.closeStatement(ps);
			BaseDao.closeConnection(conn);
		}

		return announcements;
	}

	public Announcement searchAnnouncementByAnnouncementID(int anno_id) {
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		Announcement announcement = new Announcement();
		try {
			sql = "select * from announcements where anno_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, anno_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				announcement.setAnnouncementID(rs.getInt("anno_id"));
				announcement.setAnnouncementTitle(rs.getString("title"));
				announcement.setAnnouncementContent(rs.getString("content"));
				announcement.setAnnouncementPostTime(rs.getString("post_time"));
				announcement.setAnnouncementComment(rs.getString("comment"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			BaseDao.closeResultSet(rs);
			BaseDao.closeStatement(ps);
			BaseDao.closeConnection(conn);
		}

		return announcement;
	}

	public Announcement searchAnnouncementByTitle(String anno_title) {
		Connection searchConn = BaseDao.getConnection();
		PreparedStatement ps = null;
		String sql = "select * from announcements where title = ?";
		ResultSet rs = null;
		Announcement announcement = new Announcement();
		try {
			ps = searchConn.prepareStatement(sql);
			ps.setString(1, anno_title);
			rs = ps.executeQuery();
			while (rs.next()) {
				announcement.setAnnouncementID(rs.getInt("anno_id"));
				announcement.setAnnouncementTitle(rs.getString("title"));
				announcement.setAnnouncementContent(rs.getString("content"));
				announcement.setAnnouncementPostTime(rs.getString("post_time"));
				announcement.setAnnouncementComment(rs.getString("comment"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			BaseDao.closeResultSet(rs);
			BaseDao.closeStatement(ps);
			BaseDao.closeConnection(searchConn);
		}

		return announcement;
	}
	// </editor-fold>
}
