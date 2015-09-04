package org.crama.stocktradinggame.service;

import org.crama.stacktradinggame.api.Main;
import org.crama.stacktradinggame.api.User;

public class UserService {
	private Main main;
	private static UserService instance = null;
	private UserService() {
		main = Main.getInstance();
	}
	public static synchronized UserService getInstance() {
		if (instance == null) {
			instance = new UserService();			
		}
		return instance;
	}
	public User login(String username, String password) {
		User user = main.login(username, password);
		return user;
	}
	public String register(String firstName, String lastName, String email, String password,
			String passwordConfirm) {
		String errorMessage = main.register(firstName, lastName, email, password, passwordConfirm);
		return errorMessage;
	}
}
