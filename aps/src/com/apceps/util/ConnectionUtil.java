package com.apceps.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtil {

    
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection("jdbc:sqlserver://INHYNSVENTURUP1:1433;database=apceps;", "apceps", "apceps");
	}

	public static void closeConnection(Connection connection) throws SQLException {
		if (connection != null)
			connection.close();
	}

	public static void closeConnection(Connection connection, ResultSet rs, Statement stmt) throws SQLException {

		if (rs != null)
			rs.close();

		if (stmt != null)
			stmt.close();

		if (connection != null)
			connection.close();

	}

	public static void closeConnection(Connection connection, Statement stmt) throws SQLException {
 
		if (stmt != null)
			stmt.close();

		if (connection != null)
			connection.close();

	}
	
	public static void closeConnection(ResultSet rs, PreparedStatement psmt) throws SQLException {

		if (rs != null)
			rs.close();

		if (psmt != null)
			psmt.close();
	}
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();
 		closeConnection(connection);
	}

} 
