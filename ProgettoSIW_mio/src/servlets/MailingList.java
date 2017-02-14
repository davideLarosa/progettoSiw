package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Email;
import persistence.DBManager;

public class MailingList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = (String) request.getSession().getAttribute("email");
		if (email == null) {
			response.sendRedirect("login.jsp");
		} else {
			doPost(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Email userEmail = new Email((String) request.getSession().getAttribute("email"));

		Email email = DBManager.getInstance().getMailingListDAO().findByPrimaryKey(userEmail);

		if (request.getParameterNames().hasMoreElements()) {
			String jsonString = request.getParameter("check");
			if (jsonString != null) {
				boolean valueToSet = false;
				ObjectMapper mapper = new ObjectMapper();
				valueToSet = mapper.readValue(jsonString, Boolean.class);
				if (valueToSet) {
					if (email == null) {
						DBManager.getInstance().getMailingListDAO().save(userEmail);
					} else {
						if (email != null) {
							DBManager.getInstance().getMailingListDAO().delete(userEmail);
						}
					}
				}
			}

		} else {
			if (email == null) {
				request.getSession().setAttribute("subscribed", "no");
			} else {
				request.getSession().setAttribute("subscribed", "yes");
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("notifications.jsp");
			dispatcher.forward(request, response);
		}
	}

}
