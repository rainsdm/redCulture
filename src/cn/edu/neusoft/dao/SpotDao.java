package cn.edu.neusoft.dao;

import cn.edu.neusoft.model.Spot;
import cn.edu.neusoft.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpotDao {
    Connection conn = null;
    PreparedStatement p_stmt = null;
    ResultSet rs = null;
    int spots_count;

    /**
     * 数据的初始化流程，用来获取完整的记录数量
     */
    public SpotDao() {
        String sql = "select count(*) from spots";
        conn = BaseDao.getConnection();
        try {
            p_stmt = conn.prepareStatement(sql);
            rs = p_stmt.executeQuery();
            if (rs.next()) {
                this.spots_count = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDao.closeResultSet(rs);
            BaseDao.closeStatement(p_stmt);
        }
    }

    public int getSpots_count() {
        return spots_count;
    }

    /**
     * 可以分页查询景点的数据。目前规定，每页一个数据。
     * 当每页有多个数据时，我需要调整limit startIndex,step的参数。
     * @param page 起始页的索引。小于零时，视为不分页查询。如果页面超过总数量，提示索引超出范围，仅给出最后一页的信息。并打印最后一页。
     * @param pageSize 每页显示的数量。
     * @return 最终的分页数据。
     */
    public List<Spot> searchAllSpots(int page, int pageSize){
        List<Spot> spots = new ArrayList<>();
        conn = BaseDao.getConnection();

        String sql = "";
        if (page < 0 ) {
            sql = "select * from spots";
            try {
                p_stmt = conn.prepareStatement(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (page < this.spots_count) {
            sql = "select * from spots order by spot_id limit ?,?";
            try {
                p_stmt = conn.prepareStatement(sql);
                p_stmt.setInt(1,(page-1)*pageSize);
                p_stmt.setInt(2,pageSize);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } // 索引超出限制的时候，仅返回最后一条。否则，功能太过于复杂。
        else {
            sql = "select * from spots order by spot_id desc limit 0, 1";
            try {
                p_stmt = conn.prepareStatement(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            rs = p_stmt.executeQuery();
            while (rs.next()) {
                Spot spot = new Spot();
                spot.setSpot_id(rs.getString("spot_id"));
                spot.setSpot_name(rs.getString("spot_name"));
                spot.setLocation(rs.getString("location"));
                spot.setHistory(rs.getString("history"));

                spots.add(spot);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDao.closeResultSet(rs);
            BaseDao.closeStatement(p_stmt);
        }

        return spots;
    }

    public Spot searchSpotById(int spot_id) {
        Connection searchConn = this.conn;
        PreparedStatement ps = null;
        String sql = "select * from spots where spot_id = ?";
        ResultSet rs = null;
        Spot spot = new Spot();
        try {
            ps = searchConn.prepareStatement(sql);
            ps.setInt(1, spot_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                spot.setSpot_id(rs.getString("spot_id"));
                spot.setSpot_name(rs.getString("spot_name"));
                spot.setLocation(rs.getString("location"));
                spot.setHistory(rs.getString("history"));
            }
            return spot;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDao.closeResultSet(rs);
            BaseDao.closeStatement(ps);
        }
    }

    public List<Spot> getPopularSpots(){
        List<Spot> spots = new ArrayList<>();
        conn = BaseDao.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String sql = "select * from spots_visit_num";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Spot spot = new Spot();
                spot.setSpot_id(rs.getString("spot_id"));
                spot.setSpot_name(rs.getString("spot_name"));

                spots.add(spot);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDao.closeResultSet(rs);
            BaseDao.closeStatement(ps);
            BaseDao.closeConnection(conn);
        }

        return spots;
    }
}
