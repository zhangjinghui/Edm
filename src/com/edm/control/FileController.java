package com.edm.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edm.service.FileServiceImpl;
import com.edm.service.IFileService;
import com.edm.vo.Files;
import com.edm.vo.User;

@WebServlet(name = "FileController", urlPatterns = { "/file" })
public class FileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IFileService service = new FileServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		RequestDispatcher dispatcher = null;
		String username = null;

		switch (action) {
		case "uploadfile":
			username = ((User) request.getSession().getAttribute("user"))
					.getUsername();
			response.setContentType("text/html");
			if (service.upload(request, username)) {
				response.getWriter().print("<h2>上传成功！</h2>");
			} else {
				response.getWriter().print("<h2>上传失败！</h2>");
			}
			break;
		case "delete":
			response.setContentType("text/html");
			Integer id = Integer.parseInt(request.getParameter("id"));
			if (service.delete(id)) {
				response.getWriter().print("<h2>删除成功！</h2>");
			} else {
				response.getWriter().print("<h2>删除失败！</h2>");
			}
			break;
		case "showfile":
			String from = request.getParameter("from");
			username = ((User) request.getSession().getAttribute("user"))
					.getUsername();
			List<Files> list = service.showFile(username);
			request.setAttribute("list", list);
			request.setAttribute("index", request.getParameter("index"));
			if (from != null) {
				dispatcher = request.getRequestDispatcher("/data/newData.jsp");
			} else {
				dispatcher = request
						.getRequestDispatcher("/files/existingFiles.jsp");
			}
			break;

		case "edit":
			String filename = request.getParameter("filename");
			username = ((User) request.getSession().getAttribute("user"))
					.getUsername();
			String path = "DataAnalysis\\" + username + "\\" + filename;
			request.setAttribute("path", path);
			dispatcher = request.getRequestDispatcher("/files/edit.jsp");
			break;
		case "pic":
			InputStream is = new FileInputStream(
					new File(
							request.getSession().getServletContext()
									.getRealPath("\\")
									+ "DataAnalysis\\"
									+ ((User) request.getSession()
											.getAttribute("user"))
											.getUsername() + "/svm.jpg"));
			ServletOutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = is.read(b)) > 0) {
				os.write(b, 0, length);
			}
			os.close();
			is.close();
			break;
		default:
			break;
		}
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}
	}
}
