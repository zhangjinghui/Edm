package com.edm.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0 {
	private static DataSource ds = null;
	private static Connection conn = null;
	static {
		ds = new ComboPooledDataSource("MySQL");
	}

	public static Connection getConnection() {
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) {
		System.out.println(C3P0.getConnection());
	}
}
