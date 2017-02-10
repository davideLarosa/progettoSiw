package model;

public class Item {
	private int id;
	private String name;
	private String category;
	private String seller;

	public Item() {
		super();
	}

	public Item(int id, String name, String category, String seller) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.seller = seller;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}
	
	

}
