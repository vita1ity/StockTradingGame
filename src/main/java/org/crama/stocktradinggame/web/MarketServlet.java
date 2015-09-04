package org.crama.stocktradinggame.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.crama.stacktradinggame.api.User;
import org.crama.stacktradinggame.model.StockUserResponse;
import org.crama.stocktradinggame.service.MarketService;



@WebServlet(
		name = "MarketServlet",
		urlPatterns = {"/market"}
		)
public class MarketServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5306097107645613930L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MarketService marketService = MarketService.getInstance();
		User loginUser = (User)request.getSession().getAttribute("loginUser");
		List<StockUserResponse> stockSet = null;
		if (loginUser == null) {
			stockSet = marketService.getStockPage(1);
		}
		else {
			stockSet = marketService.getStockPage(loginUser, 1);
		}
		int numberOfPages = marketService.getStockPagesNumber();
		request.setAttribute("numberOfPages", numberOfPages);
		request.setAttribute("currentPage", 1);
		request.setAttribute("stockSet", stockSet);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/market.jsp");
		rd.forward(request, response);
		
	}

}
