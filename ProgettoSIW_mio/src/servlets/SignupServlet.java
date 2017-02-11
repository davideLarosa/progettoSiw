package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import persistence.DBManager;

public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// public SignupServlet() {
	// super();
	// }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User registeringUser = new User();
		registeringUser.setName(request.getParameter("name"));
		registeringUser.setSurname(request.getParameter("surname"));
		registeringUser.setPhone(request.getParameter("phone"));
		registeringUser.setAddress(request.getParameter("address"));
		registeringUser.setEmail(request.getParameter("email"));
		registeringUser.setPassword(request.getParameter("password"));
		String confirm = request.getParameter("confirm");
		registeringUser.setSeller(Boolean.parseBoolean(request.getParameter("seller")));

		if (confirm.equals(registeringUser.getPassword())) {
			System.out.println(DBManager.getInstance().getUserDAO().save(registeringUser));
			request.getSession().setAttribute("ok_message", "Registration successfull");
			response.sendRedirect(request.getHeader("referer"));
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			System.out.println("Signup error");
			request.getSession().setAttribute("error_message", "Password doesn't matches");
			response.sendRedirect(request.getHeader("referer"));
			response.setStatus(HttpServletResponse.SC_OK);
		}
	}
}
