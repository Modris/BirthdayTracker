package com.modris.Controller;

import java.io.IOException;
import java.util.UUID;

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
 * Servlet implementation class RegisterGuestServlet
 */
@WebServlet("/registerGuest")
public class RegisterGuestServlet extends HttpServlet {
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
		String guestId = (String) session.getAttribute("username");

		if (guestId == null) {
			guestId = "Guest" + UUID.randomUUID().toString();
			session.setAttribute("guestId", guestId);
		}
		String passwordPlainText = "guest";
		String hashedPassword = BCrypt.hashpw(passwordPlainText, BCrypt.gensalt());
		// BCrypt.checkpw(passwordPlainText, hashedPassword));
		logDao.register(guestId, hashedPassword);
		logDao.getPassword(guestId); // sets CredsId. Gets password from database.
		session.setAttribute("username", guestId);
		session.setAttribute("credsId", logDao.getCredsId());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/View/index.jsp");
		dispatcher.forward(request, response);
	}
}
