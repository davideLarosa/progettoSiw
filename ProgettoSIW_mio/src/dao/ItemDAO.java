package dao;

import java.util.ArrayList;

import model.Item;

public interface ItemDAO{
	public int save(Item item);  // Create
	public Item findByPrimaryKey(String itemId);     // Retrieve
	public Item findByPrimaryKey(Item item);     // Retrieve
	public void setPath(int item_id, ArrayList<String> paths);
	public ArrayList<String> findAllUserItems(String email);  
	public void update(Item item); //Update
	public void delete(Item item); //Delete	

}
