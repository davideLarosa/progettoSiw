package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import persistence.DBManager;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// redirect to index
		// request.setAttribute("name", request.getParameter("nome"));
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
		// response.setStatus(HttpServletResponse.SC_OK);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User tryLogin = DBManager.getInstance().getUserDAO().findByPrimaryKey(email, password);

		if (email != null && password != null) {
			if (tryLogin != null) {
				System.out.println("User logging in:" + tryLogin.getName());
				HttpSession session = request.getSession(true);
				if (session.getAttribute("username") != tryLogin.getName()) {
					request.getSession().setAttribute("username", tryLogin.getName());
					request.getSession().setAttribute("email", tryLogin.getEmail());
				}
				response.sendRedirect("index.jsp");
				response.setStatus(HttpServletResponse.SC_OK);
			} else {
				request.getSession().setAttribute("loginMessage", "Wrong username or password");
				response.sendRedirect("login.jsp");
				response.setStatus(HttpServletResponse.SC_OK);
			}
		}

	}
}
