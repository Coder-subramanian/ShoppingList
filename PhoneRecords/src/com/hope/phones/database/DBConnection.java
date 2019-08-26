package com.hope.phones.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static final String url = "jdbc:mysql://localhost:3306/ramdb?autoReconnect=true&useSSL=false";
	public static final String userName = "root";
	public static final String pass ="root";
	
	public static Connection connection = null;
	
	public static Connection getConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(url, userName, pass);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return connection;
		
	}

}
