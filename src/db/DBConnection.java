package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static DBConnection con;
	private static Connection connection;

	private DBConnection() {
		try {
			/*
			 * properties = new Properties(); properties.load(new
			 * FileInputStream("app.properties")); String driver =
			 * properties.getProperty("mySQLdriver"); String url =
			 * properties.getProperty("mySQLurl"); String username =
			 * properties.getProperty("username"); String password =
			 * properties.getProperty("password");
			 */
			String url = DBConstants.url;
			String username = DBConstants.username;
			String password = DBConstants.password;
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static Connection getDBInstance() {
		if (con == null) {
			con = new DBConnection();
		}
		return connection;
	}

	public static void CloseConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
