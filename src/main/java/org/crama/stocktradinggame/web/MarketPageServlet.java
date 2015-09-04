package org.crama.stocktradinggame.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.crama.stacktradinggame.api.User;
import org.crama.stacktradinggame.model.StockUserResponse;
import org.crama.stocktradinggame.service.MarketService;

import com.google.gson.Gson;

@WebServlet(
		name = "MarketPageServlet",
		urlPatterns = {"/marketPage"}
		)
public class MarketPageServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4608710791338802235L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		int page = Integer.parseInt(request.getParameter("page"));
		
		//System.out.println("Page: " + page);
		
		MarketService marketService = MarketService.getInstance();
		User loginUser = (User)request.getSession().getAttribute("loginUser");
		List<StockUserResponse> stockSet = null;
		if (loginUser == null) {
			stockSet = marketService.getStockPage(page);
		}
		else {
			stockSet = marketService.getStockPage(loginUser, page);
		}
		String jsonStocks = new Gson().toJson(stockSet);

		response.getWriter().write(jsonStocks);

		/*int numberOfPages = marketService.getStockPagesNumeber();
		request.setAttribute("numberOfPages", numberOfPages);
		request.setAttribute("currentPage", 1);
		request.setAttribute("stockSet", stockSet);*/
		
	}
}
