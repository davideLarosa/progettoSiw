package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.User;
import persistence.DBManager;

public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// public SignupServlet() {
	// super();
	// }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameterNames().hasMoreElements()) {
			String jsonString = request.getParameter("user");

			if (jsonString != null) {
				ObjectMapper mapper = new ObjectMapper();
				User user = mapper.readValue(jsonString, User.class);

				int signupResponse = DBManager.getInstance().getUserDAO().save(user);

				System.out.println(signupResponse);
				System.out.println(user.getName() + " " + user.getSurname() + " " + user.getEmail() + " "
						+ user.getPhone() + " " + user.getAddress() + " " + user.getPassword() + " " + user.isSeller());

				if (signupResponse > 0) {
					response.getWriter().println(signupResponse);
//					response.sendError(signupResponse);
					response.setStatus(HttpServletResponse.SC_OK);
				}
				if (signupResponse == 0) {
					response.getWriter().print(signupResponse);
					response.setStatus(HttpServletResponse.SC_OK);
				}
			}
		}
	}

}
