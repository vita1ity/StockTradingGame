package org.crama.stocktradinggame.service;

import java.util.Map;

import org.crama.stacktradinggame.api.Order;
import org.crama.stacktradinggame.api.Stock;
import org.crama.stacktradinggame.api.User;

public class AccountService {
	
	private static AccountService instance = null;
	private AccountService() {
	}
	public static synchronized AccountService getInstance() {
		if (instance == null) {
			instance = new AccountService();			
		}
		return instance;
	}
	public Map<Stock, Order> getUserStocks(User user) {
		return user.getUserStocks();
		
	}
	public Map<Stock, Order> getRequestedStocks(User user) {
		return user.getOrderedStocks();
	}
	
	
}
