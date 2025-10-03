package cn.edu.neusoft.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 基础的数据库访问类。
 */
public class BaseDao {
	public static final String URL = "jdbc:mysql://localhost:3306/redCulture";
	public static final String USER = "Robert";
	public static final String PASSWORD = "nuO8yi5VQApmAZCQ";

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			System.out.println("驱动连接失败！");
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}

		return conn;
	}

	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static void closeStatement(PreparedStatement pre_stmt) {
		if (pre_stmt != null) {
			try {
				pre_stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
