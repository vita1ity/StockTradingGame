package org.crama.stocktradinggame.service;

import org.crama.stacktradinggame.api.Main;
import org.crama.stacktradinggame.api.Order;
import org.crama.stacktradinggame.api.Side;
import org.crama.stacktradinggame.api.Stock;
import org.crama.stacktradinggame.api.User;
import org.crama.stacktradinggame.model.StockUserRelation;
import org.crama.stacktradinggame.model.StockUserResponse;

public class StockService {
	private Main main;
	private static StockService instance = null;
	private StockService() {
		main = Main.getInstance();
	}
	public static synchronized StockService getInstance() {
		if (instance == null) {
			instance = new StockService();			
		}
		return instance;
	}
	public StockUserResponse getStock(String stockCode, User loginUser) {
		Stock stock = main.getStock(stockCode);
		
		System.out.println(stock);
		
		if (stock == null) {
			return null;
		}
		else {
			
			return createStockUserResponse(loginUser, stock);
		}
		 
	}
	public StockUserResponse buyStock(User loginUser, String stockCode, int price) {
		Stock stock = main.getStock(stockCode);
		System.out.println(loginUser);
		
		if (price >= stock.getSellPrice() || loginUser.getFreeItemsLeft() > 0) {
			boolean added = loginUser.addStockToUser(stock, price);
			if (added) {
				Order order = loginUser.removePlacedOrder(stock);
				stock.setOwner(loginUser);
				stock.setLastTraded(price);
				stock.setSellPrice(price);
				main.setStock(stock);
				main.setOrderBuyPrice(loginUser, stock);
				
				//Order order = new Order(stock.getCode(), loginUser.getEmail(), Side.BUY, price);
				//main.addOrder(order);
			}
			
		}
		else {
			loginUser.placeBuyOrder(stock, price);
			if (price > stock.getBuyPrice()) {
				stock.setBuyPrice(price);
				Order order = new Order(stock.getCode(), loginUser.getEmail(), Side.BUY, price);
				stock.setBestBuyOrder(order);
				main.setStock(stock);
				main.addOrder(order);
			}
		}
		
		return createStockUserResponse(loginUser, stock);
		
	}
	
	public StockUserResponse cancelOrder(User loginUser, String stockCode) {
		Stock stock = main.getStock(stockCode);
		Order order = loginUser.removePlacedOrder(stock);
		main.cancelOrder(order);
		main.setOrderBuyPrice(loginUser, stock);
		return createStockUserResponse(loginUser, stock);
	}
	
	public StockUserResponse sellStock(User loginUser, String stockCode, int price) {
		Stock stock = main.getStock(stockCode);
		loginUser.placeSellOrder(stock, price);
		if (price <= stock.getBuyPrice()) {
			String buyerMail = stock.getBestBuyOrder().getUserEmail();
			User buyer = main.getUser(buyerMail);
			boolean added = buyer.addStockToUser(stock, price);
			Order order = buyer.removePlacedOrder(stock);
			main.cancelOrder(order);
			if (added) {
				stock.setOwner(buyer);
				stock.setLastTraded(price);
				stock.setSellPrice(price);
				
				main.setStock(stock);
				main.setOrderBuyPrice(buyer, stock);
			}
		}
		else {
			stock.setSellPrice(price);
			main.setStock(stock);
		}
		return createStockUserResponse(loginUser, stock);
	}
	
	private StockUserResponse createStockUserResponse(User loginUser, Stock stock) {
		StockUserResponse response = null;
		if (loginUser == null) {
			response = new StockUserResponse(stock, null, null, 1);
		}
		else if (loginUser.containsStock(stock.getCode())) {
			Order order = loginUser.getBoughtOrder(stock.getCode());
			response = new StockUserResponse(stock, StockUserRelation.BOUGHT, order, loginUser, 1);
		}
		else if (loginUser.hasPlacedOrder(stock.getCode())) {
			
			System.out.println("HAS PLACED ORDER: " + stock.getCode() + ", " + loginUser.getEmail());
			
			Order order = loginUser.getPlacedOrder(stock.getCode());
			response = new StockUserResponse(stock, StockUserRelation.PLACED_ORDER, order, loginUser, 1);
		}
		else {
			response = new StockUserResponse(stock, StockUserRelation.AVAILABLE, null, loginUser, 1);
		}
		return response;
	}
	public void addStocksToUser(String[] stocks, User loginUser) {
		for (String stockCode: stocks) {
			Stock stock = main.getStock(stockCode);
			System.out.println(loginUser);
			int price = stock.getSellPrice();
			
			boolean added = loginUser.addStockToUser(stock, price);
			if (added) {
				Order order = loginUser.removePlacedOrder(stock);
				stock.setOwner(loginUser);
				stock.setLastTraded(price);
				stock.setSellPrice(price);
				main.setStock(stock);
				main.setOrderBuyPrice(loginUser, stock);
				
				//Order order = new Order(stock.getCode(), loginUser.getEmail(), Side.BUY, price);
				//main.addOrder(order);
			}
			
		}
		loginUser.setFreeItemsLeft(0);
	}
	
}
