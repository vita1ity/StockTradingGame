package org.crama.stacktradinggame.api;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Order implements Comparable<Order>{
	private String id;
	private String stockCode;
	private String userEmail;
	private Side side;
	private int price;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Side getSide() {
		return side;
	}
	public void setSide(Side side) {
		this.side = side;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public int getOrder() {
		return price;
	}
	public void setOrder(int price) {
		this.price = price;
	}
	
/*	public Order(String stockCode, String userEmail, int price) {
		super();
		
		this.stockCode = stockCode;
		this.userEmail = userEmail;
		this.price = price;
	}*/
	
	public Order(String stockCode, String userEmail, Side side, int price) {
		super();
		this.id = generateClientOrderId(userEmail, stockCode, side);
		this.stockCode = stockCode;
		this.userEmail = userEmail;
		this.side = side;
		this.price = price;
	}
	
	private String generateClientOrderId(String brokerCode, String stockCode, Side side) {
        return brokerCode + "-"
                + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime())
                + "-" + stockCode + "-" + side;
    }
	@Override
	public int compareTo(Order o) {
		return o.getPrice() - this.price;

	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", stockCode=" + stockCode + ", userEmail=" + userEmail + ", side=" + side
				+ ", price=" + price + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + price;
		result = prime * result + ((side == null) ? 0 : side.hashCode());
		result = prime * result + ((stockCode == null) ? 0 : stockCode.hashCode());
		result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (price != other.price)
			return false;
		if (side != other.side)
			return false;
		if (stockCode == null) {
			if (other.stockCode != null)
				return false;
		} else if (!stockCode.equals(other.stockCode))
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		return true;
	}

	
	
}
