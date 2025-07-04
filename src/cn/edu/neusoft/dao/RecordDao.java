package cn.edu.neusoft.dao;

import cn.edu.neusoft.model.Records;
import cn.edu.neusoft.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecordDao {
    Connection conn = null;
    private User user = null;

    public RecordDao() {
        getConnection();
    }

    public RecordDao(User loggedUser) {
        getConnection(); // 它似乎在初始化时，必定会自动调用一次无参构造函数。......它根本不会。
        this.user = loggedUser;
    }

    public Connection getConnection() {
        conn = BaseDao.getConnection();

        return conn;
    }

    /**
     * 学习打卡。
     * @param record 打卡记录详情。
     * @return 成功返回新增记录的ID，否则返回0。
     */
    public int addRecord(Records record) {
        int updatedRecord = 0;
        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "insert into records(user_id, spot_id, produce_time) values(?,?,?)";
        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, Integer.parseInt(record.getUser_id()));
            ps.setInt(2, record.getSpot_id());
            ps.setTimestamp(3, record.getProduce_time());
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                updatedRecord = rs.getInt(1);
                /*
                 * 这里必须用整数来处理，因为它不一定会返回和我原始数据库一样的内容。
                 * 它可以考虑用指定列名的方式来获取数据。但是，这样很明显会在数据库列发生变化时，
                 * 这个地方也要一起修改。
                 */
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDao.closeResultSet(rs);
            BaseDao.closeStatement(ps);
            BaseDao.closeConnection(conn);
        }

        return updatedRecord;
    }

    public boolean deleteRecord(int record_id) {
        int num = 0;
        Connection conn = getConnection();
        PreparedStatement ps = null;

        String sql = null;
        if (this.user.getRole() == 0) {
            sql = "delete from records where record_id = ?";
            try {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, record_id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            sql = "delete from records where user_id = ? and record_id = ?";
            try {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(this.user.getUser_id()));
                ps.setInt(2, record_id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            num = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDao.closeStatement(ps);
            BaseDao.closeConnection(conn);
        }
        return num > 0;
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

    /**
     * 查找某个时间段的学习记录。
     *
     * @param timeList 包含了开始时间和结束时间的列表。
     * @return 查询到的学习记录信息。
     */
    public List<Records> searchRecordByTime(List<String> timeList) {
        Connection conn = getConnection();
        List<Records> recordsList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select * from records where produce_time >= ? and produce_time < ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, timeList.get(0));
            ps.setObject(2, timeList.get(1));
            rs = ps.executeQuery();

            while (rs.next()) {
                Records records = new Records();
                records.setRecord_id(rs.getInt("record_id"));
                records.setUser_id(rs.getString("user_id"));
                records.setSpot_id(rs.getInt("spot_id"));
                records.setProduce_time(rs.getTimestamp("produce_time"));
                records.setLearn_note(rs.getString("learn_note"));

                recordsList.add(records);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDao.closeResultSet(rs);
            BaseDao.closeStatement(ps);
            BaseDao.closeConnection(conn);
        }

        return recordsList;
    }

    public List<Records> searchAllRecord() {
        Connection conn = getConnection();
        List<Records> recordsList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "";
            if (this.user.getRole() == 0) {
                sql = "select * from records";
                ps = conn.prepareStatement(sql);
            } else {
                sql = "select * from records where user_id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(this.user.getUser_id()));
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                Records records = new Records();
                records.setRecord_id(rs.getInt("record_id"));
                records.setUser_id(rs.getString("user_id"));
                records.setSpot_id(rs.getInt("spot_id"));
                records.setProduce_time(rs.getTimestamp("produce_time"));
                records.setLearn_note(rs.getString("learn_note"));

                recordsList.add(records);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDao.closeResultSet(rs);
            BaseDao.closeStatement(ps);
            BaseDao.closeConnection(conn);
        }

        return recordsList;
    }

    public Records searchRecordByRecordID(int record_id) {
        Connection searchConn = getConnection();
        PreparedStatement ps = null;
        String sql = null;
        ResultSet rs = null;
        Records record = new Records();
        if (this.user.getRole() == 0) {
            sql = "select * from records where record_id = ?";
            try {
                ps = searchConn.prepareStatement(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            sql = "select * from records where record_id = ? and user_id = ?";
            try {
                ps = searchConn.prepareStatement(sql);
                ps.setInt(1, record_id);
                ps.setInt(2, Integer.parseInt(this.user.getUser_id()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            rs = ps.executeQuery();
            while (rs.next()) {
                record.setRecord_id(rs.getInt("record_id"));
                record.setUser_id(rs.getString("user_id"));
                record.setSpot_id(rs.getInt("spot_id"));
                record.setProduce_time(rs.getTimestamp("produce_time"));
                record.setLearn_note(rs.getString("learn_note"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDao.closeResultSet(rs);
            BaseDao.closeStatement(ps);
            BaseDao.closeConnection(searchConn);
        }

        return record;
    }

    public List<Records> searchRecordBySpotID(int spot_id) {
        Connection searchConn = getConnection();
        PreparedStatement ps = null;
        String sql = null;
        if (this.user.getRole() == 0) {
            sql = "select * from records where spot_id = ?";
            try {
                ps = searchConn.prepareStatement(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            sql = "select * from records where user_id = ? and spot_id = ?";
            try {
                ps = searchConn.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(this.user.getUser_id()));
                ps.setInt(2, spot_id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        ResultSet rs = null;
        List<Records> recordsList = new ArrayList<>();
        try {
            rs = ps.executeQuery();
            while (rs.next()) {
                Records record = new Records();
                record.setRecord_id(rs.getInt("record_id"));
                record.setUser_id(rs.getString("user_id"));
                record.setSpot_id(rs.getInt("spot_id"));
                record.setProduce_time(rs.getTimestamp("produce_time"));
                record.setLearn_note(rs.getString("learn_note"));

                recordsList.add(record);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDao.closeResultSet(rs);
            BaseDao.closeStatement(ps);
            BaseDao.closeConnection(searchConn);
        }

        return recordsList;
    }
}
