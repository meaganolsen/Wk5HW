package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	private final static String URL = "jdbc:mysql://localhost:3306/animalsDB";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "Kincheloe@1988";
	private static Connection connection;
	private static DBConnect myConnection;
	
	private DBConnect(Connection connection) {
		this.connection = connection;
	}
	
	public static Connection getConnection() {
		if (myConnection == null) {
			try {
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				myConnection = new DBConnect(connection);
				System.out.println("Successfully Connected!");
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return DBConnect.connection;
	}
}
