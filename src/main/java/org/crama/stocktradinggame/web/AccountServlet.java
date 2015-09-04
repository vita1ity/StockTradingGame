package org.crama.stocktradinggame.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.crama.stacktradinggame.api.Order;
import org.crama.stacktradinggame.api.Stock;
import org.crama.stacktradinggame.api.User;
import org.crama.stocktradinggame.service.AccountService;

@WebServlet(
		name = "AccountServlet",
		urlPatterns = {"/account"}
		)
public class AccountServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6652478989073122437L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AccountService accountService = AccountService.getInstance();
		User loginUser = (User)request.getSession().getAttribute("loginUser");
		if (loginUser == null) {
			response.sendRedirect("market");
		}
		else {
			Map<Stock, Order> userStocks = accountService.getUserStocks(loginUser);
			Map<Stock, Order> requestedStocks = accountService.getRequestedStocks(loginUser);
			
			System.out.println("User stocks:");
			for ( Map.Entry<Stock,Order> stockEntry: userStocks.entrySet()) {
				Stock stock = stockEntry.getKey();
				System.out.println(stock);
			}
			
			System.out.println("Requested stocks:");
			for ( Map.Entry<Stock,Order> stockEntry: requestedStocks.entrySet()) {
				Stock stock = stockEntry.getKey();
				System.out.println(stock);
			}
			
			request.setAttribute("userStocks", userStocks);
			request.setAttribute("requestedStocks", requestedStocks);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/account.jsp");
			rd.forward(request, response);
		}
	}
}
