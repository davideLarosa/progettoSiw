package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.DBManager;

public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("search value is: " + request.getParameter("search"));
		String referer = request.getParameter("from");
		if (request.getParameter("id") != null && !request.getParameter("id").equals("")) {
			System.out.println("get if");
			doPost(request, response);
		} else {
			request.getRequestDispatcher(referer).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String addToCart = request.getParameter("id");
		String referer = request.getParameter("from");

		DBManager.getInstance().getUserDAO().addToCart((String) request.getSession().getAttribute("email"),
				(Integer.valueOf(addToCart) - 1029384756));
		request.setAttribute("search", request.getParameter("search"));
		request.getRequestDispatcher(referer).forward(request, response);

	}

}
