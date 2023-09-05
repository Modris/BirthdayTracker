package com.modris.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.modris.Model.TrackerDao;

/**
 * Servlet implementation class deleteServlet
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TrackerDao dao;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		dao = new TrackerDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int credsId = (int) session.getAttribute("credsId");
		dao.setCredsId(credsId);

		int id = Integer.parseInt(request.getParameter("idDelete"));
		dao.delete(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/listAll");
		dispatcher.forward(request, response);
	}

}
