package dao;

import java.util.ArrayList;
import java.util.List;

import model.CompleteItem;
//import model.Cart;
import model.User;

public interface UserDAO {
	public int save(User user); // Create

	public User findByPrimaryKey(String email, String password); // Retrieve

	public int getUserID(String email);
	
	public User findByPrimaryKey(String email);

	public List<User> findAll();

	public ArrayList<CompleteItem> getCartPaths(String email);

	// public Cart getCart(String email);
	// public int getCartId(String email);
	public int addToCart(String email, int item_id);

	public void removeFromCart(String email, int item_id);

	public void update(User user); // Update

	public void delete(User user); // Delete
}
