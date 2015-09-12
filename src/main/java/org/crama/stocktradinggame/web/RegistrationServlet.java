package org.crama.stocktradinggame.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.crama.stacktradinggame.api.Stock;
import org.crama.stacktradinggame.api.User;
import org.crama.stocktradinggame.service.MarketService;
import org.crama.stocktradinggame.service.StockService;
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
		
		MarketService marketService = MarketService.getInstance();
		List<Stock> allStocks = marketService.getAllStocks();
		
		request.setAttribute("allStocks", allStocks);
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
		
		String firstStock = request.getParameter("firstStock");
		String secondStock = request.getParameter("secondStock");
		String thirdStock = request.getParameter("thirdStock");
		String forthStock = request.getParameter("forthStock");
		String fifthStock = request.getParameter("fifthStock");
		String[] stocks = new String[5];
		stocks[0] = firstStock;
		stocks[1] = secondStock;
		stocks[2] = thirdStock;
		stocks[3] = forthStock;
		stocks[4] = fifthStock;
		
		UserService userService = UserService.getInstance();
		String errorMessage = userService.register(firstName, lastName, email, password, passwordConfirm);
		
		if (errorMessage != null) {
			request.setAttribute("errorMessage", errorMessage);
			MarketService marketService = MarketService.getInstance();
			List<Stock> allStocks = marketService.getAllStocks();
			
			request.setAttribute("allStocks", allStocks);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/registration.jsp");
			rd.forward(request, response);
		}
		else {
			
			User loginUser = userService.login(email, password);
			
			if (loginUser == null) {
				request.setAttribute("errorMessage", "Invalid password or username");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
				rd.forward(request, response);
			}
			else {
				StockService stockService = StockService.getInstance();
				stockService.addStocksToUser(stocks, loginUser);
				
				HttpSession session = request.getSession(true); 
				session.setAttribute("loginUser", loginUser);
				
				response.sendRedirect(referrer);
				//response.sendRedirect(request.getParameter("from"));
			}
		
		}
	}
}
