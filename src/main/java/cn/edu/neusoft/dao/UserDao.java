package cn.edu.neusoft.dao;

import cn.edu.neusoft.dto.user.auth.request.CreateUserRequest;
import cn.edu.neusoft.dto.user.auth.response.UserAuthInfo;
import cn.edu.neusoft.model.User;
import cn.edu.neusoft.model.modPassword;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class UserDao {
    Connection conn = null;

    public UserDao() {
        getConnection();
    }

    public Connection getConnection() {
        conn = BaseDao.getConnection();

        return conn;
    }

    //<editor-fold desc="查找用户">
    
    /**
     * 根据用户名返回对应的登录响应信息。
     * @param userName 要精确查找的用户名称。
     * @return 服务器对登录信息的响应数据。如果未查询到信息，默认为空文本。<br>
     * 否则，返回查询到的用户名、密码，用于数据比对。
     */
    public UserAuthInfo findAuthInfoByUsername(String username) {
    	Connection searchConn = this.conn;
        PreparedStatement ps = null;
        String sql = "select username, password from users where username = ?";
        ResultSet rs = null;
        UserAuthInfo uai;
        try {
            ps = searchConn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                String userName = rs.getString("username");
                String password = rs.getString("password");
                uai = new UserAuthInfo(userName, password);
            } else {
				uai = new UserAuthInfo("", "");
			}

            rs.close();
            ps.close();

            return uai;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

	/**
     * 按用户名查找用户信息。<br>
     * 如果存在重复的用户名，就只能找出第一个用户的信息。
     * @param userName 要精确查找的用户名称。
     * @return 完整的用户信息。不包括时间信息。如果返回 null，表示不存在这个用户。
     */
    @Deprecated
    public User findByUsername(String userName) {
        Connection searchConn = this.conn;
        PreparedStatement ps = null;
        String sql = "select * from users where username = ?";
        ResultSet rs = null;
        User user = new User();
        try {
            ps = searchConn.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            if (rs.next()) {
                user.setUser_id(rs.getString("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("role"));
                user.setStudyPoints(rs.getInt("points"));
            } else {
                user = null;
            }

            rs.close();
            ps.close();

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 按用户名查找用户信息。<br>
     * 如果存在重复的用户名，就只能找出第一个用户的信息。它理论上更加安全。因为，这是一个不再存储密码的新模型。<br><br>
     * TODO: 一旦全部迁移过来后，重命名为 findByUsername 。
     * @param userName 要精确查找的用户名称。
     * @return 完整的用户信息。包括时间信息。如果返回 null，表示不存在这个用户。
     */
    public User findByUsernameWithTimestamps(String userName) {
        Connection searchConn = this.conn;
        PreparedStatement ps = null;
        String sql = "select * from users where username = ?";
        ResultSet rs = null;
        User user = new User();
        try {
            ps = searchConn.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            if (rs.next()) {
                user.setUser_id(rs.getString("user_id"));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getInt("role"));
                user.setStudyPoints(rs.getInt("points"));
                Timestamp timestamp = rs.getTimestamp("create_time");
                user.setCreateTime(timestamp.toLocalDateTime());
                timestamp = rs.getTimestamp("last_accessed_time");
                if (timestamp != null) {
                	user.setLastAccessedTime(timestamp.toLocalDateTime());
                }
            } else {
                user = null;
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
                user.setStudyPoints(rs.getInt("points"));
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
                user.setStudyPoints(rs.getInt("points"));
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

        User u = new User();
        try {
            ps = searchConn.prepareStatement(sql);
            ps.setInt(1, u.stringToRole(role));
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getString("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("role"));
                user.setStudyPoints(rs.getInt("points"));

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


    /**
     * 向数据库用户表插入用户
     *
     * @param user 存储数据表信息的模型
     * @return 1 插入成功 -1 失败
     */
    public int insertUser(CreateUserRequest user) {
        Connection conn = this.conn;
        int result;

        PreparedStatement ps = null;
        int rows;

        String sql = "insert into users(username, password, role) values (?, ?, ?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.username());
            ps.setString(2, user.password());
            ps.setInt(3, user.role());

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
        //TODO: 建议使用0作为插入数据失败的返回值。
        return result;
    }

    /**
     * 根据ID删除用户。
     *
     * @param user 待删除用户的全部信息。
     * @return 如果删除成功，返回true，否则返回false。
     */
    public int deleteUser(User user) {
        Connection conn = this.conn;
        PreparedStatement ps = null;
        String sql = "delete from users where user_id = ?";
        int result = 0;
        if (user.getUserId() == null || user.getUserId().isEmpty()) {
            return result;
        }
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUserId());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDao.closeStatement(ps);
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
                user.setStudyPoints(rs.getInt("points"));
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

    public int addPoints(String user_id, int add_points) {
        Connection conn = this.conn;
        User user = searchByUserID(user_id);
        int new_points = user.getStudyPoints() + add_points;
        int result = 0;

        String sql = "update users set study_points = ? where user_id = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, new_points);
            ps.setString(2, user_id);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDao.closeStatement(ps);
        }

        return result;
    }

}
