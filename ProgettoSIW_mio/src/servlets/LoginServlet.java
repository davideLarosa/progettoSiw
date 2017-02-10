package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		request.setAttribute("name", request.getParameter("nome"));
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
		dispatcher.forward(request, response);
		// response.setStatus(HttpServletResponse.SC_OK);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameterNames().hasMoreElements()) {

			String jsonString = request.getParameter("guest");
			System.out.println(jsonString);
			if (jsonString != null) {
				ObjectMapper mapper = new ObjectMapper();
				User user = mapper.readValue(jsonString, User.class);
				System.out.println("user " + user.getEmail() + ", password " + user.getPassword() + " keep signed "
						+ user.isKeepSigned());

				User registeredUser = DBManager.getInstance().getUserDAO().findByPrimaryKey(user.getEmail());

				// System.out.println(user.getEmail());
				// System.out.println(registeredUser.getEmail());

				if (registeredUser != null && user != null) {
					if (registeredUser.getPassword().equals(user.getPassword())) {
						response.getWriter().print("0");
						response.setStatus(HttpServletResponse.SC_OK);
					} else {
						response.getWriter().print("1");
						response.setStatus(HttpServletResponse.SC_OK);
					}
				} else {
					response.getWriter().print("1");
					response.setStatus(HttpServletResponse.SC_OK);
				}
				// if (registeredUser == null) {
				//// response.getWriter().print("1");
				//// response.setStatus(HttpServletResponse.SC_OK);
				// }

				// // TODO setup loggedIn variable to view name on index
				// boolean loggedIn = false;
				//
				// if (loggedIn) {
				// doGet(request, response);
				// } else {
				//
				// response.getWriter()
				// .println("<p style=\"text-align:center; color:red;\"> Wrong
				// username or password</p>");
				//
				// response.setStatus(HttpServletResponse.SC_OK);
				// }

			}
		}
	}
}
