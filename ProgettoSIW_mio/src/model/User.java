package model;

import java.util.List;

public class User {

	private int id;
	private String name;
	private String surname;
	private String phone;
	private String address;
	private String email;
	private String password;
	private String confirm;
	private boolean keepSigned;
	private boolean seller;

	public User() {
		super();
	}

	public User(int id, String name, String surname, String phone, String address, String email, String password,
			boolean keepSigned, boolean seller) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.password = password;
		this.keepSigned = keepSigned;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCart(List<Item> items) {

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isKeepSigned() {
		return keepSigned;
	}

	public void setKeepSigned(boolean keepSigned) {
		this.keepSigned = keepSigned;
	}

	public boolean isSeller() {
		return seller;
	}

	public void setSeller(boolean seller) {
		this.seller = seller;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
}
