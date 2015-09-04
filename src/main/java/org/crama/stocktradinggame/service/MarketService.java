package org.crama.stocktradinggame.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.crama.stacktradinggame.api.Main;
import org.crama.stacktradinggame.api.Order;
import org.crama.stacktradinggame.api.Stock;
import org.crama.stacktradinggame.api.User;
import org.crama.stacktradinggame.model.StockUserRelation;
import org.crama.stacktradinggame.model.StockUserResponse;


public class MarketService {
	
	private static final int ITEMS_PER_PAGE = 8;
	
	private Main main;
	private static MarketService instance = null;
	private MarketService() {
		main = Main.getInstance();
	}
	public static synchronized MarketService getInstance() {
		if (instance == null) {
			instance = new MarketService();			
		}
		return instance;
	}
	
	public List<StockUserResponse> getStockPage(int page) {
		List<StockUserResponse> stockList = new ArrayList<StockUserResponse>();
		List<Stock> stocks = main.getAvailableStocks();
		Collections.sort(stocks);
		int startItem = ITEMS_PER_PAGE * (page - 1);
		int endItem = startItem + ITEMS_PER_PAGE - 1;
		int i = 0;
		for (Stock s: stocks) {
			if (i > endItem) {
				break;
			}
			if (i >= startItem) {
				StockUserResponse response = new StockUserResponse(s, null, null, page);
				stockList.add(response);
			}
			++i;
			
		}
		return stockList;
	}
	public List<StockUserResponse> getStockPage(User loginUser, int page) {
		List<StockUserResponse> stockList = new ArrayList<StockUserResponse>();
		List<Stock> stocks = main.getAvailableStocks();
		Collections.sort(stocks);
		int startItem = ITEMS_PER_PAGE * (page - 1);
		int endItem = startItem + ITEMS_PER_PAGE - 1;
		int i = 0;
		for (Stock s: stocks) {
			if (i > endItem) {
				break;
			}
			if (i >= startItem) {
				StockUserResponse response = null;
				if (loginUser.containsStock(s.getCode())) {
					Order order = loginUser.getBoughtOrder(s.getCode());
					response = new StockUserResponse(s, StockUserRelation.BOUGHT, order, loginUser, page);
				}
				else if (loginUser.hasPlacedOrder(s.getCode())) {
					Order order = loginUser.getPlacedOrder(s.getCode());
					response = new StockUserResponse(s, StockUserRelation.PLACED_ORDER, order, loginUser, page);
				}
				else {
					response = new StockUserResponse(s, StockUserRelation.AVAILABLE, null, loginUser, page);
				}
				stockList.add(response);
			}
			
			++i;
			
		}
		return stockList;
	}
	public int getStockPagesNumber() {
		List<Stock> stocks = main.getAvailableStocks();
		int num = 0;
		if (stocks.size() % ITEMS_PER_PAGE != 0) {
			num = stocks.size() / ITEMS_PER_PAGE + 1;
		}
		else {
			num = stocks.size() / ITEMS_PER_PAGE;
		}
		return num;
	}
	
}
