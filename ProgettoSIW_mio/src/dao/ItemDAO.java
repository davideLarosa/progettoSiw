package dao;

import java.util.ArrayList;

import model.CompleteItem;
import model.Item;

public interface ItemDAO {
	public int save(Item item); // Create

	public Item findByPrimaryKey(String itemId); // Retrieve

	public void setPath(int item_id, ArrayList<String> paths);

	public ArrayList<CompleteItem> getCartPaths(String email);

	public ArrayList<CompleteItem> findAllUserItems(String email);

	public ArrayList<CompleteItem> findItems(String searchQuery);

	public ArrayList<CompleteItem> findItemsPerCategory(String searchQuery);

	public ArrayList<CompleteItem> findItemsPerProducer(String searchQuery);

	public ArrayList<String> findAllProducers();

	public void update(Item item); // Update

	public void delete(int id, String email); // Delete

	public void makeBid(int itemToBid);
}
