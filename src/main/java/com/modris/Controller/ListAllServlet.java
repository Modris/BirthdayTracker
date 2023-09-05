package com.modris.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.modris.Model.Tracker;
import com.modris.Model.TrackerDao;

/**
 * Servlet implementation class listAllServlet
 */
@WebServlet("/listAll")
public class ListAllServlet extends HttpServlet {
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

		List<Tracker> trackerList = new ArrayList<>();
		String order = sortBy(request);
		// System.out.println(order);
		trackerList = dao.listAll(order);

		request.setAttribute("trackerList", trackerList);
		request.setAttribute("order99", order);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/View/index.jsp");
		dispatcher.forward(request, response);

	}

	private boolean validateInput(HttpServletRequest request) {
		if (request.getAttribute("order5") == null) {
			return true;
		}
		return false;
	}

	private String sortBy(HttpServletRequest request) {
		boolean answer = validateInput(request);
		if (answer) {
			return "sortByDefault";
		}
		return request.getAttribute("order5").toString();
	}

}
