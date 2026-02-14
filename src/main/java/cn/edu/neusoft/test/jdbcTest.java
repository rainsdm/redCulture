package cn.edu.neusoft.test;

import cn.edu.neusoft.dao.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class jdbcTest {
    void main() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        conn = BaseDao.getConnection();
        String sql = "select * from users";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                IO.println(rs.getString("username"));
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            IO.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
