package org.crama.stocktradinggame.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.crama.stacktradinggame.api.User;
import org.crama.stacktradinggame.model.StockUserResponse;
import org.crama.stocktradinggame.service.StockService;

import com.google.gson.Gson;
@WebServlet (
		name = "CancelOrderServlet",
		urlPatterns = {"/cancel-order/*"}
		)
public class CancelOrderServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3985502439580966990L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String[] pathInfo = request.getPathInfo().split("/");
		String stockCode = null;
		if (pathInfo.length == 0) {
			System.out.println("No path variables");
			response.sendRedirect("market");
		}
		else {
			stockCode = pathInfo[1];
			User loginUser = (User)request.getSession().getAttribute("loginUser");
			StockService stockService = StockService.getInstance();
			
			StockUserResponse stock = stockService.cancelOrder(loginUser, stockCode);
			
			String jsonStock = new Gson().toJson(stock);
			response.getWriter().write(jsonStock);
		}
	}
}
