package com.edm.service;

import java.io.File;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.synth.SynthSpinnerUI;

import com.edm.dao.FileDAOImpl;
import com.edm.dao.IFileDAO;
import com.edm.vo.Files;
import com.oreilly.servlet.MultipartRequest;

public class FileServiceImpl implements IFileService {
	private IFileDAO dao = new FileDAOImpl();

	public boolean upload(HttpServletRequest request, String username) {
		Files file = new Files();
		MultipartRequest mr;
		try {
			String path = request.getSession().getServletContext().getRealPath("\\")+"DataAnalysis\\" + username + "\\";
			File p = new File(path);
			if (!p.exists()) {
				p.mkdir();
			}
			mr = new MultipartRequest(request, path, 10 * 1024 * 1024, "utf-8");
			file.setFileName(mr.getFilesystemName("file"));
			file.setDate(new Date(System.currentTimeMillis()));
			file.setAnalysisResult("");
			file.setUserName(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao.upload(file);
	}

	public List<Files> showFile(String username) {
		return dao.showFile(username);
	}

	public boolean delete(Integer id) {
		return dao.delete(id);
	}

}
