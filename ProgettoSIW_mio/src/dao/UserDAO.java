package dao;

import java.util.List;

import model.Cart;
import model.User;

public interface UserDAO {
	public int save(User user);  // Create
	public User findByPrimaryKey(String email, String password);     // Retrieve
	public User findByPrimaryKey(String email);
	public List<User> findAll();
	public Cart getCart(String email);
	public int getCartId(String email);
	public int addToCart(String email, int item_id);
	public void update(User user); //Update
	public void delete(User user); //Delete	
}
