package org.crama.stocktradinggame.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.crama.stocktradinggame.service.UserService;

@WebServlet(
		name = "RedistrationServlet",
		urlPatterns = {"/registration"}
		)
public class RegistrationServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3505996013165207898L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String referrer = request.getHeader("Referer");
		request.setAttribute("referrer", referrer);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/registration.jsp");
		rd.forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String referrer = request.getParameter("from");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String passwordConfirm = request.getParameter("passwordConfirmation");
		String password = request.getParameter("password");
		
		UserService userService = UserService.getInstance();
		String errorMessage = userService.register(firstName, lastName, email, password, passwordConfirm);
		
		if (errorMessage != null) {
			request.setAttribute("errorMessage", errorMessage);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/registration.jsp");
			rd.forward(request, response);
		}
		else {
			
			response.sendRedirect(referrer);
			//response.sendRedirect(request.getParameter("from"));
		}
	}
}
