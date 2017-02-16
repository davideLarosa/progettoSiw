package dao;

import java.util.List;

import model.Item;
import model.User;
import persistence.DataSource;

public class ItemDAOJDBC implements ItemDAO {

	private DataSource dataSource;
	
	public ItemDAOJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int save(Item item) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Item findByPrimaryKey(String itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> findAll(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Item item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Item item) {
		// TODO Auto-generated method stub

	}

}
