package com.edm.control;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edm.service.IUserService;
import com.edm.service.UserServiceImpl;
import com.edm.vo.User;

@WebServlet(name = "UserController", urlPatterns = { "/user" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IUserService service = new UserServiceImpl();       

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		String action = request.getParameter("action");
		RequestDispatcher dispatcher = null;

		switch (action) {
		case "login":
			user.setUsername(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));

			User user2 = service.login(user);
			if (user2 != null) {
				request.getSession().setAttribute("user", user2);
				dispatcher = request.getRequestDispatcher("/index.html");
			} else {
				dispatcher = request.getRequestDispatcher("/login.html");
			}
			break;
		case "exit":
			request.getSession().invalidate();
			response.sendRedirect("login.html");
			break;
		case "register":
			user.setUsername(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));
			user.setEmail(request.getParameter("email"));
			user.setRealname(request.getParameter("realname"));
			user.setPhone(request.getParameter("phone"));

			if (service.register(user)) {
				File file = new File(request.getSession().getServletContext().getRealPath("\\")+"DataAnalysis\\" + user.getUsername());
				file.mkdir();
				request.getSession().setAttribute("user", user);
				dispatcher = request.getRequestDispatcher("/index.html");
			} else {
				dispatcher = request.getRequestDispatcher("/login.html");
			}
			break;
		case "query":
			String username = request.getParameter("username");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if (service.query(username)) {
				out.println("true");
			} else {
				out.println("false");
			}
			out.flush();
			out.close();
			break;
		default:
			break;
		}
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}
	}
}
