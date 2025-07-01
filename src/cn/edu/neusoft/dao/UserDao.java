package cn.edu.neusoft.dao;

import cn.edu.neusoft.model.User;
import cn.edu.neusoft.model.modPassword;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/********************************************
 * 方法名：
 * 功能：
 * 方法参数：
 * 方法返回值：
 *****************************************/
public class UserDao {
    public static final String URL = "jdbc:mysql://localhost:3306/redCulture";
    public static final String USER = "Robert";
    public static final String PASSWORD = "nuO8yi5VQApmAZCQ";
    Connection conn = null;


    public Connection getConnection() {
        conn = BaseDao.getConnection();

        return conn;
    }

    public UserDao() {
        getConnection();
    }

    //<editor-fold desc="查找用户"

    public User searchByUsername(String userName) {
        Connection searchConn = this.conn;
        PreparedStatement ps = null;
        String sql = "select * from users where username = ?";
        ResultSet rs = null;
        User user = new User();
        try {
            ps = searchConn.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setUser_id(rs.getString("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("role"));
                user.setStudy_points(rs.getInt("study_points"));
            }

            rs.close();
            ps.close();

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User searchByUserID(String userID) {
        Connection searchConn = this.conn;
        PreparedStatement ps = null;
        String sql = "select * from users where user_id = ?";
        ResultSet rs = null;
        User user = new User();
        try {
            ps = searchConn.prepareStatement(sql);
            ps.setString(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setUser_id(rs.getString("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("role"));
                user.setStudy_points(rs.getInt("study_points"));
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDao.closeResultSet(rs);
            BaseDao.closeStatement(ps);
        }
    }

    public List<User> searchByRole(int role) {
        Connection searchConn = this.conn;
        PreparedStatement ps = null;
        String sql = "select * from users where role = ?";
        ResultSet rs = null;
        List<User> users = new ArrayList<User>();

        User user = new User();
        try {
            ps = searchConn.prepareStatement(sql);
            ps.setInt(1, role);
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setUser_id(rs.getString("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("role"));
                user.setStudy_points(rs.getInt("study_points"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDao.closeResultSet(rs);
            BaseDao.closeStatement(ps);
        }
        return users;
    }

    public List<User> searchByRole(String role) {
        Connection searchConn = this.conn;
        PreparedStatement ps = null;
        String sql = "select * from users where role = ?";
        ResultSet rs = null;
        List<User> users = new ArrayList<User>();

        User user = new User();
        try {
            ps = searchConn.prepareStatement(sql);
            ps.setInt(1, user.stringToRole(role));
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setUser_id(rs.getString("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("role"));
                user.setStudy_points(rs.getInt("study_points"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDao.closeResultSet(rs);
            BaseDao.closeStatement(ps);
        }
        return users;
    }

    //</editor-fold>

//    /********************************************
//     * 方法名：registerUser
//     * 功能：向数据库用户表插入用户
//     * 方法参数：User对象
//     * 方法返回值：1 插入成功 -1 失败
//     *****************************************/
    /**
     * 向数据库用户表插入用户
     * @param user 存储数据表信息的模型
     * @return 1 插入成功 -1 失败
     */
    public int insertUser(User user) {
        Connection conn = this.conn;
        int result;

        PreparedStatement ps = null;
        int rows;

        String sql = "insert into users(username, password, role) values (?, ?, ?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                ps.setString(2, user.getPassword());
            } else {
                System.out.println();
            }
            ps.setInt(3, user.getRole());

            rows = ps.executeUpdate();
            if (rows > 0) {
                result = 1;
            } else {
                result = -1;
            }

            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 修改用户表的密码。
     *
     * @param modPassword 传输过来的密码信息。
     * @return 1表示修改成功，-1 失败
     */
    public int changePassword(modPassword modPassword) {
        Connection conn = this.conn;
        int result;

        PreparedStatement p_stmt = null;
        int rows;

        String sql = "update users set password = ? where user_id = ?";
        try {
            p_stmt = conn.prepareStatement(sql);
            p_stmt.setString(1, modPassword.getNewPassword());
            p_stmt.setString(2, modPassword.getUserID());

            rows = p_stmt.executeUpdate();
            result = rows > 0 ? 1 : -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public List<User> searchAllUsers() {
        Connection conn = this.conn;
        PreparedStatement p_stmt = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<User>();

        try {
            String sql = "select * from users";
            p_stmt = conn.prepareStatement(sql);
            rs = p_stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getString("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("role"));
                user.setStudy_points(rs.getInt("study_points"));
                users.add(user);
//                user.clear();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDao.closeResultSet(rs);
            BaseDao.closeStatement(p_stmt);
        }

        return users;
    }
}
