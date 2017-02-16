package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Item;
import model.User;
import persistence.DataSource;
import persistence.PersistenceException;

public class ItemDAOJDBC implements ItemDAO {

	private DataSource dataSource;
	
	public ItemDAOJDBC(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public int save(Item item) {
		
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into item(category, seller) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setInt(1, item.getCategory().getId());
			System.out.println("category id " + item.getCategory().getId());
			statement.setInt(2, item.getSeller().getId());
			System.out.println("seller id " + item.getSeller().getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
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
