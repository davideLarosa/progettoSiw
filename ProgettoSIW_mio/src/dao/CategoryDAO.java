package dao;

import java.util.List;

import model.Category;

public interface CategoryDAO {
	//TODO
	//public int save(User user); // Create

	public Category findByPrimaryKey(String name); // Retrieve

	public List<Category> findAll();

	// TODO
	// public void update(User user); // Update
	// TODO
	// public void delete(User user); // Delete

}
