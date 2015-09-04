package org.crama.stacktradinggame.model;

import org.crama.stacktradinggame.api.Order;
import org.crama.stacktradinggame.api.Stock;
import org.crama.stacktradinggame.api.User;

public class StockUserResponse {
	private Stock stock;
	private StockUserRelation relation;
	private Order order;
	private User user;
	
	private int page;
	
	public StockUserResponse(Stock stock, StockUserRelation relation, Order order, int page) {
		super();
		this.stock = stock;
		this.relation = relation;
		this.order = order;
		this.page = page;
		
	}
	
	public StockUserResponse(Stock stock, StockUserRelation relation, Order order, User user, int page) {
		super();
		this.stock = stock;
		this.relation = relation;
		this.order = order;
		this.user = user;
		this.page = page;
		
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}


	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public StockUserRelation getRelation() {
		return relation;
	}
	public void setRelation(StockUserRelation relation) {
		this.relation = relation;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	
}
