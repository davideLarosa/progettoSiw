package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cart;
import persistence.DBManager;

public class GetCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		DBManager.getInstance().getUserDAO().addToCart("a@a.a", 1);
//		DBManager.getInstance().getUserDAO().addToCart("a@a.a", 5);
//		DBManager.getInstance().getUserDAO().addToCart("a@a.a", 6);
		
		Cart cart = DBManager.getInstance().getUserDAO().getCart((String) request.getSession().getAttribute("email"));

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("cart id " + cart.getId());
		System.out.println("cart user id " + cart.getUserId());
		System.out.println("items:");
		for (int i : cart.getItems()) {
			System.out.println(i);
		}
		System.out.println();
		System.out.println();
		System.out.println();
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

}
