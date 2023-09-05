package com.modris.Controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.modris.Model.InsertService;
import com.modris.Model.TrackerDao;

/**
 * Servlet implementation class editSavedServlet
 */
@WebServlet("/editSaved")
public class EditSavedServlet extends HttpServlet {
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

		String name = request.getParameter("name").toString();
		int age = Integer.parseInt(request.getParameter("age"));
		int month = Integer.parseInt(request.getParameter("month"));
		int day = Integer.parseInt(request.getParameter("day"));
		int id = Integer.parseInt(request.getParameter("id"));
		InsertService ins = new InsertService(dao);
		day = ins.setValidDay(month, day);
		int birthY = ins.getBirthYear(age, month, day);
		LocalDate birthYear = LocalDate.of(birthY, month, day);
		dao.update(name, age, birthYear, id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/listAll");
		dispatcher.forward(request, response);
	}

}
