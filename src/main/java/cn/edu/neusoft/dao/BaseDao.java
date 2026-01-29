package cn.edu.neusoft.dao;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 基础的数据库访问类。
 */
public class BaseDao {
	public static final String URL = "jdbc:mysql://localhost:3307/redCulture"; // 端口转发测试
	public static final String USER = "redCulture_admin";
	public static final String PASSWORD = "hdea4bdxqascLgey";

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			IO.println("驱动连接失败！");
			/*
			 * 在加入SSH端口转发的初步数据库与开发环境分离技术后，第一次出现驱动连接失败的情况。
			 * 返回的主要错误信息是：Communications link failure。它能够成功向服务器发送信息，
			 * 但是无法接收到响应。
			 * 这是因为忘记开启端口转发后，后台去尝试访问3307端口时，发现这个端口收不到任何有效数据，
			 * 数据请求被扔到虚空了。
			 */
			if (e instanceof CommunicationsException) {
				IO.println("【网络异常】 无法连接到数据库服务器。");
				IO.println("如果是远程连接，请检查SSH或VPN是否已开启；");
				IO.println("如果是本地连接，请检查端口号是否正确。");
			}
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
