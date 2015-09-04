package org.crama.stocktradinggame.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.crama.stacktradinggame.api.User;
import org.crama.stacktradinggame.model.StockUserResponse;
import org.crama.stocktradinggame.service.StockService;

@WebServlet (
		name = "StockItemServlet",
		urlPatterns = {"/stock/*"}
		)
public class StockItemServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3930258757789108637L;
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			StockUserResponse stock = stockService.getStock(stockCode, loginUser);
			request.setAttribute("stock", stock);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/stock.jsp");
			rd.forward(request, response);
		}
	}
}
