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
 * Servlet implementation class RegisterSavedServlet
 */
@WebServlet("/registerSaved")
public class RegisterSavedServlet extends HttpServlet {
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
		HttpSession session = request.getSession(true);
		String username = request.getParameter("username");
		String passwordPlainText = request.getParameter("password");
		if (logDao.checkNicknameAvailability(username)) {
			request.setAttribute("error", "Unavailable username.");
			RequestDispatcher dispatcher3 = request.getRequestDispatcher("/View/register.jsp");
			dispatcher3.forward(request, response);

		} else {
			String hashedPassword = BCrypt.hashpw(passwordPlainText, BCrypt.gensalt());
			logDao.register(username, hashedPassword);
			logDao.getPassword(username); // sets CredsId
			session.setAttribute("username", username);
			// System.out.println(logDao.getCredsId());
			session.setAttribute("credsId", logDao.getCredsId());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/View/index.jsp");
			dispatcher.forward(request, response);
		}
	}

}
