package com.edm.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.edm.db.C3P0;
import com.edm.vo.Files;

public class FileDAOImpl implements IFileDAO {
	private Connection conn = C3P0.getConnection();

	public boolean upload(Files file) {
		PreparedStatement pstmt = null;
		String sql = "insert into files (filename, uploadtime, username) values(?,?,?)";
		boolean flag = false;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, file.getFileName());
			pstmt.setDate(2, (Date) file.getDate());
			pstmt.setString(3, file.getUserName());
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

	public List<Files> showFile(String username) {
		List<Files> list = new ArrayList<Files>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from files where username = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Files file = new Files();
				file.setId(rs.getInt("id"));
				file.setFileName(rs.getString("filename"));
				file.setDate(rs.getDate("uploadtime"));
				file.setAnalysisResult(rs.getString("analysisresult"));
				file.setUserName(rs.getString("username"));
				list.add(file);
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
		return list;
	}

	@Override
	public boolean delete(Integer id) {
		PreparedStatement pstmt = null;
		String sql = "delete from files where id=?";
		boolean flag = false;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
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

	@Override
	public boolean analysis(Integer id) {
		PreparedStatement pstmt = null;
		String sql = "update files set analysisresult='已分析' where id=?";
		boolean flag = false;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
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

}
