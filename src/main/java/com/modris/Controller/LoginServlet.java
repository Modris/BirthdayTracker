package com.modris.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.modris.Model.LoginDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LoginDao logDao;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		logDao = new LoginDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String hashedPassword = logDao.getPassword(username); // also sets CredsId
		if (hashedPassword.isEmpty() || !BCrypt.checkpw(password, hashedPassword)) {
			request.setAttribute("error", "Invalid Username or Password");
			RequestDispatcher dispatcher2 = request.getRequestDispatcher("/View/login.jsp");
			dispatcher2.forward(request, response);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("credsId", logDao.getCredsId());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/View/index.jsp");
			dispatcher.forward(request, response);
		}
	}

}
