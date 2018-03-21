package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public final class ConnectionUtil {

	private static Logger logger = Logger.getLogger(ConnectionUtil.class);
	
	
	/* Make Tomcat know which database driver to use */
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(ClassNotFoundException e) {
			logger.warn("Exception thrown adding oracle driver.",e);
		}
	}
	
	/* Get connection to JDBC */
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:oraclethin:@revdbinstance.endpoint.amazonaws.com:1521:ORCL";
		String username = "LOGIN_TEST_DB";
		String password = "p4ssw0rd";
		return DriverManager.getConnection(url, username, password);
	}
	
}
