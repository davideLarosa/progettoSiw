package dao;

import java.util.List;

import model.User;

public interface UserDAO {
	public int save(User user);  // Create
	public User findByPrimaryKey(String email, String password);     // Retrieve
	public List<User> findAll();       
	public void update(User user); //Update
	public void delete(User user); //Delete	
}
