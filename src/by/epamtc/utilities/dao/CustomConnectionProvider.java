package by.epamtc.utilities.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomConnectionProvider {
	private static final CustomConnectionProvider instance = new CustomConnectionProvider();
	
	private Connection connection;
	
	private final String URL = "jdbc:mysql://localhost/utilities_db?serverTimezone=Europe/Moscow&useSSL=false";
    private final String USER_NAME = "root";
    private final String PASSWORD = "1234";
	
	private CustomConnectionProvider() {
		
	}
	
	public static CustomConnectionProvider getInstance() {
		if (instance == null) {
			return new CustomConnectionProvider();
		}
		return instance;
	}
	
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			if (connection == null) {
				connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
	            System.out.println("CONNECTION " + connection);
	        }
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
        return connection;
    }

}
