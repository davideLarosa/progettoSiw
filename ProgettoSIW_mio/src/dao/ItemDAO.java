package dao;

import java.util.List;

import model.Item;
import model.User;

public interface ItemDAO{
	public int save(Item item);  // Create
	public Item findByPrimaryKey(String itemId);     // Retrieve
	public List<Item> findAll(User user);  
	public void update(Item item); //Update
	public void delete(Item item); //Delete	

}
