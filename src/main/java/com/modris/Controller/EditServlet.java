package com.modris.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modris.Model.TrackerDao;

/**
 * Servlet implementation class editServlet
 */
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("editName").toString();
		int age = Integer.parseInt(request.getParameter("editAge"));
		String birthDate = request.getParameter("editDate");
		int id = Integer.parseInt(request.getParameter("editId"));
		int month = Integer.parseInt(birthDate.substring(5, 7));
		int day = Integer.parseInt(birthDate.substring(8));
		request.setAttribute("editName", name);
		request.setAttribute("editAge", age);
		request.setAttribute("editMonth", month);
		request.setAttribute("editDay", day);
		request.setAttribute("editId", id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/View/editPage.jsp");
		dispatcher.forward(request, response);

	}

}
