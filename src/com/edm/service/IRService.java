package com.edm.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IRService {
	public void randomForest(HttpServletRequest request,
			HttpServletResponse response);

	public void supportVectorMachine(HttpServletRequest request,
			HttpServletResponse response);

	public void decisionTree(HttpServletRequest request,
			HttpServletResponse response);

	public void knn(HttpServletRequest request, HttpServletResponse response);

	public void mars(HttpServletRequest request, HttpServletResponse response);

}
