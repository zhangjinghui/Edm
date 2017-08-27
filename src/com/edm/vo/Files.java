package com.edm.vo;

import java.io.Serializable;
import java.util.Date;

public class Files implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String fileName;
	private Date date;
	private String analysisResult;
	private String userName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAnalysisResult() {
		return analysisResult;
	}

	public void setAnalysisResult(String analysisResult) {
		this.analysisResult = analysisResult;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Files [id=" + id + ", fileName=" + fileName + ", date=" + date
				+ ", analysisResult=" + analysisResult + ", userName="
				+ userName + "]";
	}

}
