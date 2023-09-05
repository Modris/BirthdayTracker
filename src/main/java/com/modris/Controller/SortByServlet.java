package com.modris.Controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modris.Model.InsertService;
import com.modris.Model.TrackerDao;

@WebServlet("/sortBy")
public class SortByServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String order = request.getParameter("sortOptions").toString();
		request.setAttribute("order5", order);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/listAll");
		dispatcher.forward(request, response);
	}

}
