package com.kaoni.ch15.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * 
 *
 * desc 	: 멤버 DAO 만들기 (싱글톤)
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 23.
 * @Version : 1.0.0
 */
public class MemberDao {
	private static MemberDao instance = new MemberDao();

	private MemberDao() {}

	public static MemberDao getMemberDao() {
		return instance;
	}

	private Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://localhost:3306/test";
			String user = "long";
			String password = "ekgp0516";
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB연결 실패");
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	public String memberLogin(String memberId, String memberPw) {
		String result = null;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT member_name FROM member WHERE member_id=? AND member_password=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getString("member_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
