package org.crama.stacktradinggame.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Main {
	
	private static final String PASSWORDS_DONT_MATCH = "Passwords are not equal";
	private static final String USER_ALREADY_EXISTS = "User with given e-mail already exist in the system";
	private static final String WRONG_EMAIL_FORMAT = "Wrong e-mail format";
	
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private List<Stock> stockList;
	private Set<User> userSet;
	
    private Set<Order> allOpenOrders;
    private Set<Order> allClosedOrders;
	
	private static Main instance = null;
	private Main() {
		stockList = new ArrayList<Stock>();
		addStocks();
		userSet = new HashSet<User>();
		addUsers();
		allOpenOrders = new HashSet<Order>();
		allClosedOrders = new HashSet<Order>();
	}
	public static synchronized Main getInstance() {
		if (instance == null) {
			instance = new Main();			
		}
		return instance;
	}
	
	
	/*public Main() {
		stockList = new ArrayList<Stock>();
		addStocks();
		userSet = new HashSet<User>();
		addUsers();
		
	}*/
	private void addUsers() {
		User user1 = new User(1, "David", "Tompson", "david.tompson@gmail.com", "david", "a.png", 1000);
		user1.setFreeItemsLeft(0);
		for (Stock s: stockList) {
			if (s.getCode().equals("1") || s.getCode().equals("2")) {
				user1.addStockToUser(s, 200);
				s.setOwner(user1);
			}
			else if (s.getCode().equals("3") || s.getCode().equals("4")) {
				user1.placeBuyOrder(s, 300);
			}
		}
		
		userSet.add(user1);
		user1 = new User(2, "Rahim", "Memphis", "rahim.memphis@gmail.com", "rahim", "a.png", 1000);
		userSet.add(user1);
		
	}
	private void addStocks() {
		Stock stock1 = new Stock("1", "Michael", "Schumacher", "Schumacher.jpg", 100);
		stock1.setUp(true);
		stockList.add(stock1);
		stock1 = new Stock("2", "Volodymyr", "Klychko", "Klychko.jpg", 150);
		stock1.setUp(true);
		stockList.add(stock1);
		stock1 = new Stock("3", "Roger", "Federer", "Federer.jpg", 130);
		stockList.add(stock1);
		stock1 = new Stock("4", "Steven", "Gerrard", "Gerrard.jpg", 200);
		stock1.setDescription("Steven George Gerrard, MBE (born 30 May 1980) is an English professional footballer who "
				+ "plays for American club LA Galaxy. He spent the majority of his career playing for Premier League club "
				+ "Liverpool, with most of that time as their captain. He has played much of his career as a central midfielder, "
				+ "but has also been used as a second striker, holding midfielder, attacking midfielder, right back and right "
				+ "winger.");;
		stockList.add(stock1);
		stock1 = new Stock("5", "Shakil", "O'Neill", "ONeill.jpg", 180);
		stockList.add(stock1);
		stock1 = new Stock("6", "Maria", "Sharapova", "Sharapova.jpg", 90);
		stockList.add(stock1);
		stock1 = new Stock("7", "Zinadine", "Zidane", "Zidane.jpg", 250);
		stockList.add(stock1);
		stock1 = new Stock("8", "Magdalena", "Neuner", "Neuner.jpg", 220);
		stockList.add(stock1);
		stock1 = new Stock("9", "Domracheva", "Daria", "Domracheva.jpg", 300);
		stockList.add(stock1);
		
	}
	public List<Stock> getAvailableStocks() {
		return stockList;
	}
	public User login(String username, String password) {
		for (User user: userSet) {
			if (user.getEmail().equals(username)) {
				if (user.getPassword().equals(password)) {
					return user;
				}
			}
		}
		return null;
	}
	public String register(String firstName, String lastName, String email, String password,
			String passwordConfirm) {
		if (!password.equals(passwordConfirm)) {
			return PASSWORDS_DONT_MATCH;
		}
		else if (!email.matches(EMAIL_PATTERN)) {
			return WRONG_EMAIL_FORMAT;
		}
		for (User user: userSet) {
			if (user.getEmail().equals(email)) {
				return USER_ALREADY_EXISTS;
			}
		}
		User user = new User(firstName, lastName, email, password, "a.png", 1000);
		userSet.add(user);
		return null;
	}
	public Stock getStock(String stockCode) {
		for (Stock stock: stockList) {
			if (stock.getCode().equals(stockCode)) {
				return stock;
			}
		}
		return null;
	}
	public void setStock(Stock stock) {
		for (int i = 0; i < stockList.size(); i++) {
			Stock s = stockList.get(i);
			if (s.getCode().equals(stock.getCode())) {
				stockList.set(i, stock);
				return;
			}
		}
		
	}
	public User getUser(String userMail) {
		for (User user: userSet) {
			if (user.getEmail().equals(userMail)) {
				return user;
			}
		}
		return null;
	}
	public void addOrder(Order order) {
		allOpenOrders.add(order);
		
	}
	public void cancelOrder(Order order) {
		System.out.println("Remove order:" + order);
		boolean remove = allOpenOrders.remove(order);
		System.out.println(remove);
		allClosedOrders.add(order);
	}
	public void setOrderBuyPrice(User loginUser, Stock stock) {
		List<Order> orders = getStockBuyOrders(stock);
		if (orders != null && orders.size() != 0) {
			Collections.sort(orders);
			System.out.println("Greatest order:" + orders.get(0));
			stock.setBestBuyOrder(orders.get(0));
			stock.setBuyPrice(orders.get(0).getPrice());
			setStock(stock);
		}
		else {
			stock.setBestBuyOrder(null);
			stock.setBuyPrice(0);
			setStock(stock);
		}
	}
	private List<Order> getStockBuyOrders(Stock stock) {
		List<Order> orders = new ArrayList<Order>();
		for (Order order: allOpenOrders) {
			System.out.println(order);
			if (order.getStockCode().equals(stock.getCode()) && order.getSide().equals(Side.BUY)) {
				orders.add(order);
			}
		}
			
		return orders;
	}
	
}
