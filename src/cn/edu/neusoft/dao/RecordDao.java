package cn.edu.neusoft.dao;

import cn.edu.neusoft.model.Records;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RecordDao {
    Connection conn = null;

    public Connection getConnection() {
        conn = BaseDao.getConnection();

        return conn;
    }

    public RecordDao() {
        getConnection();
    }

    /**
     * 学习打卡。
     * @param record 打卡记录详情。
     * @return 成功返回正整数，否则返回0。
     */
    public int addRecord(Records record) {
        int num = 0;
        conn = getConnection();
        PreparedStatement ps = null;

        String sql = "insert into records(user_id, spot_id, produce_time) values(?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(record.getUser_id()));
            ps.setInt(2, record.getSpot_id());
            ps.setTimestamp(3, record.getProduce_time());
            num = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDao.closeStatement(ps);
            BaseDao.closeConnection(conn);
        }
        return num;
    }

    /**
     * 增加记录以后，为刚刚增加的记录新增学习笔记。
     */
    public int addNote(Records record) {
        Connection conn = getConnection();
        int num = 0;

        String sql = "update records set learn_note = ?, produce_time = ? where user_id = ? and spot_id = ? and record_id = ?"; // 这是初次添加笔记，没必要保留旧记录，因为它本身就是空的。
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, record.getLearn_note());
            ps.setTimestamp(2, record.getProduce_time());
            ps.setInt(3, Integer.parseInt(record.getUser_id()));
            ps.setInt(4, record.getSpot_id());
            ps.setInt(5, record.getRecord_id());

            num = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDao.closeStatement(ps);
        }

        return num;
    }
}
