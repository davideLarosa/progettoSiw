package dao;

import java.util.ArrayList;

import model.CompleteItem;
import model.Item;

public interface ItemDAO{
	public int save(Item item);  // Create
	public Item findByPrimaryKey(String itemId);     // Retrieve
	public void setPath(int item_id, ArrayList<String> paths);
	public ArrayList<CompleteItem> findAllUserItems(String email);  
	public void update(Item item); //Update
	public void delete(int id, String email); //Delete	

}
