package model;

import java.util.ArrayList;

public class Cart {

	private int id;
	private int userId;
	private ArrayList<Integer> items;

	public Cart() {
		super();
		this.items = new ArrayList<Integer>();
	}

	public Cart(int id) {
		super();
		this.id = id;
		this.items = new ArrayList<Integer>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public ArrayList<Integer> getItems() {
		return items;
	}

	public void setItems(ArrayList<Integer> itemId) {
		this.items = itemId;
	}

	public void addItem(int itemId) {
		this.items.add(itemId);
	}

	public int getId(int index) {
		return this.items.get(index);
	}

}
