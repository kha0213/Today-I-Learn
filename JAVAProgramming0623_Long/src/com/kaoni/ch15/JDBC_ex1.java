package com.kaoni.ch15;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_ex1 {
	private static JDBC_ex1 instance = new JDBC_ex1();
	
	private JDBC_ex1() {}
	
	public static JDBC_ex1 getJDBC() {
		return instance;
	}
	
	private Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://localhost:3306/";
			String user = "long";
			String password = "ekgp0516";
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("DB연결 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB연결 실패");
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	
	public static void main(String[] args) {
		JDBC_ex1 ex = JDBC_ex1.getJDBC();
		Connection awa = ex.getConnection();
		System.out.println(awa);
	}
}
