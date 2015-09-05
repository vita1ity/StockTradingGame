package org.crama.stocktradinggame.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.crama.stacktradinggame.api.Stock;
import org.crama.stocktradinggame.service.MarketService;

import com.google.gson.Gson;

@WebServlet(
		name = "RefreshStocksServlet",
		urlPatterns = {"/refresh-stocks"}
		)
public class RefreshStocksServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2212839640420386021L;


	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		MarketService marketService = MarketService.getInstance();
		List<Stock> allStocks = marketService.getAllStocks();
		
		String jsonStock = new Gson().toJson(allStocks);
		response.getWriter().write(jsonStock);
		
	}
	
}
