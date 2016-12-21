package com.dusksoft.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {
	
	public static Connection getConnection() {
		try {
			InputStream in = DbUtil.class.getResourceAsStream("db_connection.properties");
			Properties properties = new Properties();
			properties.load(in);
			
			String driver = properties.getProperty("driver");
			String url = properties.getProperty("url");
			String user = properties.getProperty("user");
			String pass = properties.getProperty("pass");
			
			Class.forName(driver);
			return DriverManager.getConnection(url, user, pass);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
