package org.crama.stacktradinggame.api;

import java.util.HashMap;
import java.util.Map;

public class User {
	
	private int id;
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String imageName;
	private int accountBalance;
	
	private int freeItemsLeft;
	
	private Map<Stock, Order> userStocks;
	private Map<Stock, Order> orderedStocks;
	
	//private Set<Stock> userStocks;
	//private Set<Stock> orderedStocks;
	
	public User(int id, String firstName, String lastName, String email, String password, String imageName, int accountBalance) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.imageName = imageName;
		this.accountBalance = accountBalance;
		this.freeItemsLeft = 5;
		userStocks = new HashMap<Stock, Order>();
		orderedStocks = new HashMap<Stock, Order>();
		//userStocks = new HashSet<Stock>();
		//orderedStocks = new HashSet<Stock>();
	}
	public User(String firstName, String lastName, String email, String password, String imageName, int accountBalance) {
		super();
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.imageName = imageName;
		this.accountBalance = accountBalance;
		this.freeItemsLeft = 5;
		userStocks = new HashMap<Stock, Order>();
		orderedStocks = new HashMap<Stock, Order>();
		//userStocks = new HashSet<Stock>();
		//orderedStocks = new HashSet<Stock>();
	}
	
	public int getFreeItemsLeft() {
		return freeItemsLeft;
	}
	public void setFreeItemsLeft(int freeItemsLeft) {
		this.freeItemsLeft = freeItemsLeft;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}
	public Map<Stock, Order> getUserStocks() {
		return userStocks;
	}
	public void setUserStocks(Map<Stock, Order> userStocks) {
		this.userStocks = userStocks;
	}
	public Map<Stock, Order> getOrderedStocks() {
		return orderedStocks;
	}
	public void setOrderedStocks(Map<Stock, Order> orderedStocks) {
		this.orderedStocks = orderedStocks;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public boolean containsStock(String code) {
		for (Stock s: userStocks.keySet()) {
			if (s.getCode().equals(code)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasPlacedOrder(String code) {
		for (Stock s: orderedStocks.keySet()) {
			if (s.getCode().equals(code)) {
				return true;
			}
		}
		return false;
	}
	
	public Order getBoughtOrder(String code) {
		for (Stock s: userStocks.keySet()) {
			if (s.getCode().equals(code)) {
				return userStocks.get(s);
			}
		}
		return null;
	}
	public Order getPlacedOrder(String code) {
		for (Stock s: orderedStocks.keySet()) {
			if (s.getCode().equals(code)) {
				return orderedStocks.get(s);
			}
		}
		return null;
	}
	public boolean addStockToUser(Stock stock, int price) {
		System.out.println("accounBalance:" + accountBalance);
		if (accountBalance >= price) {
			
			Order order = new Order(stock.getCode(), this.getEmail(), Side.BUY, price);
			userStocks.put(stock, order);
			if (freeItemsLeft > 0) {
				--freeItemsLeft;
			}
			else {
				accountBalance -= price;
			}
			/*stock.setOwner(this);
			stock.setLastTraded(price);
			stock.setSellPrice(price);*/
			
			User oldOwner = stock.getOwner();
			
			if (oldOwner != null) {
				oldOwner.removeStockFromUser(stock);
				oldOwner.setAccountBalance(oldOwner.getAccountBalance() + price);
			}
			
			return true;
		}
		else return false;
	}
	private void removeStockFromUser(Stock stock) {
		userStocks.remove(stock);
	}
	public boolean placeBuyOrder(Stock stock, int price) {
		if (accountBalance >= price) { 
			Order order = new Order(stock.getCode(), this.getEmail(), Side.BUY, price);
			orderedStocks.put(stock, order);
			return true;
		}
		else return false;
	}
	public Order removePlacedOrder(Stock stock) {
		
		Order order = orderedStocks.remove(stock);
		//System.out.println(order.getStockCode() + ", " + order.getPrice());
		//System.out.println(this.hasPlacedOrder(stock.getCode()));
		return order;
	}
	public void placeSellOrder(Stock stock, int price) {
		 
		Order order = new Order(stock.getCode(), this.getEmail(), Side.SELL, price);
		userStocks.put(stock, order);
		
	}
}
