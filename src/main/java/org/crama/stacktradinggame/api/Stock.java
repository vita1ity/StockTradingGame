package org.crama.stacktradinggame.api;

public class Stock implements Comparable<Stock>{
	private String code;
	private String firstName;
	private String lastName;
	private String imageName;
	
	private int lastTraded = 0;
	private Order bestBuyOrder; 
	private int buyPrice = 0;
	private int sellPrice = 0;;
	
	private String description = "";
	
	private User owner;
	
	private boolean up = false;
	
	public Stock(String code, String firstName, String lastName, String imageName, int price) {
		super();
		this.code = code;
		this.firstName = firstName;
		this.lastName = lastName;
		this.imageName = imageName;
		this.sellPrice = price;
	}
	public Stock(String code, String firstName, String lastName, String imageName, int price, String description) {
		super();
		this.code = code;
		this.firstName = firstName;
		this.lastName = lastName;
		this.imageName = imageName;
		this.sellPrice = price;
		this.description = description;
	}
	
	public boolean getUp() {
		return up;
	}
	public void setUp(boolean up) {
		this.up = up;
	}
	public Order getBestBuyOrder() {
		return bestBuyOrder;
	}
	public void setBestBuyOrder(Order bestBuyOrder) {
		this.bestBuyOrder = bestBuyOrder;
	}
	
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public int getLastTraded() {
		return lastTraded;
	}
	public void setLastTraded(int lastTraded) {
		this.lastTraded = lastTraded;
	}
	public int getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}
	public int getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	@Override
	public String toString() {
		return "Stock [code=" + code + ", firstName=" + firstName + ", lastName=" + lastName + ", imageName="
				+ imageName + ", lastTraded=" + lastTraded + ", buyPrice=" + buyPrice + ", sellPrice=" + sellPrice
				+ ", description=" + description + ", owner=" + owner + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		Stock other = (Stock) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	@Override
	public int compareTo(Stock o) {
		if (this.getFirstName().compareTo(o.getFirstName()) == 0) {
			return this.getLastName().compareTo(o.getLastName());
		}
		return this.getFirstName().compareTo(o.getFirstName());
	}
	
	
}	
