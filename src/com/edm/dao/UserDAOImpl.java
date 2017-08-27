package com.edm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.edm.db.C3P0;
import com.edm.vo.User;

public class UserDAOImpl implements IUserDAO {
	private Connection conn = C3P0.getConnection();

	public User login(User user) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User u = null;

		try {
			pstmt = conn
					.prepareStatement("select * from user where username=? and password=?");
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				u = new User();
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setEmail(rs.getString("email"));
				u.setRealname(rs.getString("realname"));
				u.setPhone(rs.getString("phone"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return u;
	}

	public boolean register(User user) {
		PreparedStatement pstmt = null;
		boolean flag = false;

		try {
			pstmt = conn
					.prepareStatement("insert into user(username,password,email,realname,phone) values(?,?,?,?,?)");
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getRealname());
			pstmt.setString(5, user.getPhone());
			flag = pstmt.executeUpdate() > 0 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public boolean query(String username) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;

		try {
			pstmt = conn
					.prepareStatement("select * from user where username=?");
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

}
