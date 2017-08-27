package com.edm.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DateItem implements Serializable {
	private static final long serialVersionUID = 1L;

	private String fileName;
	private List<String> paramList = new ArrayList<String>();
	private String arithmetic;
	private String trainSet;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<String> getParamList() {
		return paramList;
	}

	public void setParamList(List<String> paramList) {
		this.paramList = paramList;
	}

	public String getArithmetic() {
		return arithmetic;
	}

	public void setArithmetic(String arithmetic) {
		this.arithmetic = arithmetic;
	}

	public String getTrainSet() {
		return trainSet;
	}

	public void setTrainSet(String trainSet) {
		this.trainSet = trainSet;
	}

	@Override
	public String toString() {
		return "DateItem [fileName=" + fileName + ", paramList=" + paramList
				+ ", arithmetic=" + arithmetic + ", trainSet=" + trainSet + "]";
	}

}
