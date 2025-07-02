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
}
