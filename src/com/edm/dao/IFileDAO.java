package com.edm.dao;

import java.util.List;

import com.edm.vo.Files;

public interface IFileDAO {
	boolean upload(Files file);

	List<Files> showFile(String username);

	boolean delete(Integer id);

	public boolean analysis(Integer id);
}
