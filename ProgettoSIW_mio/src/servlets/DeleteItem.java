package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.DBManager;

public class DeleteItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("id") != null && !request.getParameter("id").equals("")) {
			int id = Integer.valueOf(request.getParameter("id"));
			System.out.println("get an id " + id);
			DBManager.getInstance().getItemDAO().delete((id - 1029384756),
					(String) request.getSession().getAttribute("email"));
			request.getRequestDispatcher("myItems.jsp").forward(request, response);
		}

		else if (request.getParameter("cartItemId") != null && !request.getParameter("cartItemId").equals("")) {
			int itemToDeleteFromCart = Integer.valueOf(request.getParameter("cartItemId"));
			System.out.println("get a cartItemId " + itemToDeleteFromCart);
			DBManager.getInstance().getUserDAO().removeFromCart((String) request.getSession().getAttribute("email"),
					(itemToDeleteFromCart - 1029384756));

			request.getRequestDispatcher("cart.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

}
