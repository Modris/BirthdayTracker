package com.modris.Controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.modris.Model.InsertService;
import com.modris.Model.Tracker;
import com.modris.Model.TrackerDao;

@WebServlet("/insert")
public class InsertServlet extends HttpServlet {
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

		if (!(validateInput(request))) {
			request.setAttribute("errorMessage", "Please fill in all the required fields.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/View/index.jsp");
			dispatcher.forward(request, response);
		} else {
			String name = (String) request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			int month = Integer.parseInt(request.getParameter("month"));
			int day = Integer.parseInt(request.getParameter("day"));
			InsertService ins = new InsertService(dao);
			day = ins.setValidDay(month, day);
			ins.insert(age, name, month, day);
			RequestDispatcher dispatcher3 = request.getRequestDispatcher("/listAll");
			dispatcher3.forward(request, response);

		}
	}

	private boolean validateInput(HttpServletRequest request) {
		if (request.getParameter("age").isEmpty() || request.getParameter("month").isEmpty()
				|| request.getParameter("name").isEmpty()) {
			return false;
		}
		return true;
	}

}
