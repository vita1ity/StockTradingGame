package org.crama.stocktradinggame.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.crama.stacktradinggame.api.User;
import org.crama.stocktradinggame.service.UserService;

@WebServlet(
		name = "LoginServlet",
		urlPatterns = {"/login"}
		)
public class LoginServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4726425154372950074L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String referrer = request.getHeader("Referer");
		request.setAttribute("referrer", referrer);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
		rd.forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String referrer = request.getHeader("Referer");
		//request.setAttribute("referrer", referrer);
		String referrer = request.getParameter("from");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserService userService = UserService.getInstance();
		User loginUser = userService.login(username, password);
		
		if (loginUser == null) {
			request.setAttribute("errorMessage", "Invalid password or username");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
			rd.forward(request, response);
		}
		else {
			
			HttpSession session = request.getSession(true); 
			session.setAttribute("loginUser", loginUser);
			
			response.sendRedirect(referrer);
			//response.sendRedirect(request.getParameter("from"));
		}
	}
}
