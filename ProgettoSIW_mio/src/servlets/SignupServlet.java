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
		request.setAttribute("error_message", "messsaggio4");
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
		registeringUser.setSeller(Boolean.parseBoolean(request.getParameter("seller")));

		switch (DBManager.getInstance().getUserDAO().save(registeringUser)) {
		case 0:
			request.setAttribute("signupMessage", "Registration successfull");
			System.out.println("Registration successfull");
			break;

		case 1:
			request.setAttribute("signupMessage", "User already exists");
			System.out.println("User already exists");
			break;

		default:
			request.setAttribute("signupMessage", "Some mistery error");
			System.out.println("Some mistery error");
			break;
		}

		this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);

	}
}
