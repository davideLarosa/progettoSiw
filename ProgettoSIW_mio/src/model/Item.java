package model;

import java.sql.Date;
import persistence.DBManager;

public class Item {
	private int id;
	private String producer;
	private String model;
	private float price;
	private float lastBid;
	private Date timeToLive;
	private Category category;
	private User seller;
	private String description;
	private boolean buy_now;
	private boolean bid;

	public Item() {

	}

	public Item(int id, String producer, String model, float price, float lastBid, Date timeToLive, Category category,
			User seller, String description, boolean buy_now, boolean bid) {
		this.id = id;
		this.producer = producer;
		this.model = model;
		this.price = price;
		this.lastBid = lastBid;
		this.timeToLive = timeToLive;
		this.category = category;
		this.seller = seller;
		this.description = description;
		this.buy_now = buy_now;
		this.bid = bid;
	}

	public Item(String producer, String model, String price, float lastBid, Date timeToLive, String category,
			User seller, String description, String buy_now, String bid) {
		this.producer = producer;
		this.model = model;
		this.price = Float.valueOf(price);
		this.lastBid = lastBid;
		this.timeToLive = timeToLive;
		this.category = DBManager.getInstance().getCategoryDAO().findByPrimaryKey(category);
		this.seller = DBManager.getInstance().getUserDAO().findByPrimaryKey(seller.getEmail(), seller.getPassword());
		this.description = description;
		this.buy_now = Boolean.valueOf(buy_now);
		this.bid = Boolean.valueOf(bid);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getLastBid() {
		return lastBid;
	}

	public void setLastBid(float lastBid) {
		this.lastBid = lastBid;
	}

	public Date getTimeToLive() {
		return timeToLive;
	}

	public void setTimeToLive(Date timeToLive) {
		this.timeToLive = timeToLive;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isBuy_now() {
		return buy_now;
	}

	public void setBuy_now(boolean buy_now) {
		this.buy_now = buy_now;
	}

	public boolean isBid() {
		return bid;
	}

	public void setBid(boolean bid) {
		this.bid = bid;
	}
}
