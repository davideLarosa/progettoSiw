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
	private String imagePath;

	public Item() {

	}

	public Item(int id, String producer, String model, float price, float lastBid, Date timeToLive, Category category,
			User seller, String description, String imagePath) {
		this.id = id;
		this.producer = producer;
		this.model = model;
		this.price = price;
		this.lastBid = lastBid;
		this.timeToLive = timeToLive;
		this.category = category;
		this.seller = seller;
		this.description = description;
		this.imagePath = imagePath;
	}

	public Item(User seller, String category) {
		this.seller = DBManager.getInstance().getUserDAO().findByPrimaryKey(seller.getEmail(), seller.getPassword());
		this.category = DBManager.getInstance().getCategoryDAO().findByPrimaryKey(category);
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
