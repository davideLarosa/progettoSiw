package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.api.jdbc.Statement;

import model.Category;
import model.CompleteItem;
import model.Item;
import model.Paths;
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
		int status = -1;
		Connection connection = this.dataSource.getConnection();
		int generatedKey = 0;
		try {
			String insert = "insert into item (producer, model, price, lastBid, ttl, description, category, seller, buy_now, bid ) values (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, item.getProducer());
			statement.setString(2, item.getModel());
			statement.setFloat(3, item.getPrice());
			statement.setFloat(4, item.getLastBid());
			statement.setDate(5, item.getTimeToLive());
			statement.setString(6, item.getDescription());
			statement.setInt(7, item.getCategory().getId());
			statement.setInt(8, item.getSeller().getId());
			statement.setBoolean(9, item.isBuy_now());
			statement.setBoolean(10, item.isBid());
			statement.executeUpdate();

			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				generatedKey = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			String message = e.getMessage();
			if (message.contains("Duplicate entry")) {
				status = -2;
			}
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				status = -3;
				throw new PersistenceException(e.getMessage());
			}
		}

		if (generatedKey != 0) {
			return generatedKey;
		}
		return status;
	}

	@Override
	public Item findByPrimaryKey(String itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CompleteItem> findAllUserItems(String email) {

		Connection connection = this.dataSource.getConnection();
		Connection connection1 = this.dataSource.getConnection();
		ArrayList<CompleteItem> completeItems = new ArrayList<CompleteItem>();
		ArrayList<Item> items = new ArrayList<>();

		try {

			PreparedStatement statement;
			String query = "select * from item, user where user.email = ? && item.seller = user.id;";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Item item = new Item();
				item.setId(result.getInt(1));
				item.setProducer(result.getString(2));
				item.setModel(result.getString(3));
				item.setPrice(result.getFloat(4));
				item.setLastBid(result.getFloat(5));
				item.setTimeToLive(result.getDate(6));
				item.setDescription(result.getString(7));
				item.setCategory(new Category(result.getString(8)));
				item.setBuy_now(result.getBoolean(10));
				item.setBid(result.getBoolean(11));
				items.add(item);
			}

			connection.close();

			for (Item currentItem : items) {
				Paths paths = new Paths();
				CompleteItem completeItem = new CompleteItem();
				PreparedStatement statement1;
				String query1 = "select * from path where path.item_id = ?";
				statement1 = connection1.prepareStatement(query1);
				statement1.setInt(1, currentItem.getId());
				ResultSet result1 = statement1.executeQuery();

				while (result1.next()) {
					paths.setId(result1.getInt(1));
					paths.setItemId(result1.getInt(2));
					paths.addPath(result1.getString(3));
				}
				completeItem.setItem(currentItem);
				completeItem.setPaths(paths);
				completeItems.add(completeItem);
			}

		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection1.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return completeItems;
	}

	@Override
	public void update(Item item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Item item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPath(int item_id, ArrayList<String> paths) {

		for (String path : paths) {
			Connection connection = this.dataSource.getConnection();
			try {
				String insert = "insert into path (item_id, path) values (?,?)";
				PreparedStatement statement = connection.prepareStatement(insert);
				statement.setInt(1, item_id);
				statement.setString(2, path);
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new PersistenceException(e.getMessage());
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new PersistenceException(e.getMessage());
				}
			}
		}
	}
}
