package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.DBManager;

public class MakeBid extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession().getAttribute("email") != null
				&& !request.getSession().getAttribute("email").equals("")) {
			doPost(request, response);
		} else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int itemToBid = 0;
		String referer = request.getParameter("from");
		
		String search = request.getParameter("search");
		if (request.getParameter("id") != null && !request.getParameter("id").equals("")) {
			itemToBid = Integer.valueOf(request.getParameter("id")) - 1029384756;
			DBManager.getInstance().getItemDAO().makeBid(itemToBid);
			DBManager.getInstance().getUserDAO().addToCart((String) request.getSession().getAttribute("email"),
					itemToBid);
		}
		request.setAttribute("search", search);
		request.getRequestDispatcher(referer).forward(request, response);
	}

}
