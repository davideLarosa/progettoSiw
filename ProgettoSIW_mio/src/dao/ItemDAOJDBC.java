package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Item;
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
		try {
			String insert = "insert into item (producer, model, price, lastBid, ttl, description, category, seller, buy_now, bid ) values (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
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
		} catch (SQLException e) {
			// e.printStackTrace();
			String message = e.getMessage();
			if (message.contains("Duplicate entry")) {
				status = -2;
			}
			// throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// e.printStackTrace();
				status = -3;
				// throw new PersistenceException(e.getMessage());
			}
		}

		Item itemToReturn = findByPrimaryKey(item);
		if (itemToReturn != null) {
			return itemToReturn.getId();
		}
		return status;
	}

	@Override
	public Item findByPrimaryKey(String itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item findByPrimaryKey(Item item) {
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement statement;
			String query = "select * from item where producer = ? && model = ? && price = ? && lastBid = ? && ttl = ? && description = ? && category = ? && seller = ? && buy_now = ? && bid = ?";
			statement = connection.prepareStatement(query);
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
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				item.setId(result.getInt("id"));
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			// throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// e.printStackTrace();
				// throw new PersistenceException(e.getMessage());
			}
		}
		return item;
	}

	@Override
	public ArrayList<String> findAllUserItems(String email) {

		Connection connection = this.dataSource.getConnection();
		ArrayList<String> paths = new ArrayList<String>();
		try {

			PreparedStatement statement;
			String query = "select path from path, item, user where path.item_id = item.id && item.seller = user.id && user.email = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				String absPath = (result.getString(1));
				String[] tempPhat = absPath.split("/");
				String relativePath = "";
				for (int i = 8; i < tempPhat.length; i++) {
					if (i < tempPhat.length - 1)
						relativePath += tempPhat[i] + "/";
					else
						relativePath += tempPhat[i];
				}
				paths.add(relativePath);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return paths;
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
				// e.printStackTrace();
				// throw new PersistenceException(e.getMessage());
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// e.printStackTrace();
					// throw new PersistenceException(e.getMessage());
				}
			}
		}
	}
}
