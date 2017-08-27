package com.edm.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edm.service.IRService;
import com.edm.service.RServiceImpl;

@WebServlet(name = "DataController", urlPatterns = { "/data" })
public class DataController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IRService service = new RServiceImpl();

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		switch (method) {
		case "0":
			service.randomForest(request, response);
			break;
		case "1":
			service.supportVectorMachine(request, response);
			break;
		case "2":
			service.decisionTree(request, response);
			break;
		case "3":
			service.knn(request, response);
			break;
		case "4":
			service.mars(request, response);
			break;
		default:
			break;
		}

	}

	public void init() throws ServletException {

	}

}
