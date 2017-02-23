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
			statement.setFloat(4, 0);
			statement.setDate(5, item.getTimeToLive());
			statement.setString(6, item.getDescription());
			statement.setInt(7, item.getCategory().getId());
			statement.setInt(8, item.getSeller());
			statement.setBoolean(9, item.isBuy_now());
			statement.setBoolean(10, item.isBid());
			// statement.executeUpdate();
			statement.execute();

			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				generatedKey = resultSet.getInt(1);
				item.setId(generatedKey);
			} else {
				System.out.println("error getting id - ItemDAOJDBC");
			}

			System.out.println();
			System.out.println("Adding item to db");
			System.out.println("id : " + item.getId() + "\n producer : " + item.getProducer() + " \n model : "
					+ item.getModel() + " \n minimum buy price : " + item.getPrice() + " \n category :  "
					+ item.getCategory().getId() + " \n time : " + item.getTimeToLive() + " \n bid : " + item.isBid()
					+ " \n buy_now : " + item.isBuy_now() + " \n description :  " + item.getDescription()
					+ " \n last bid :  " + " \n user : " + item.getSeller());
			System.out.println("-------------------");
			System.out.println();

			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			String message = e.getMessage();
			if (message.contains("Duplicate entry")) {
				status = -2;
				System.out.println("Duplicate entry");
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
		ArrayList<CompleteItem> completeItems = new ArrayList<CompleteItem>();
		ArrayList<Item> items = new ArrayList<>();

		try {

			PreparedStatement statement;
			String query = "select item.* from item, user where user.email = ? && item.seller = user.id";
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
				item.setSeller(result.getInt(9));
				item.setBuy_now(result.getBoolean(10));
				item.setBid(result.getBoolean(11));
				items.add(item);
				// TODO
				// System.out.println();
				// System.out.println("item dao jdbc");
				// System.out.println("id : " + item.getId() + "\n producer : "
				// + item.getProducer() + " \n model : "
				// + item.getModel() + " \n minimum buy price : " +
				// item.getPrice() + " \n category : "
				// + item.getCategory().getId() + " \n time : " +
				// item.getTimeToLive() + " \n bid : " + item.isBid()
				// + " \n buy_now : " + item.isBuy_now() + " \n description : "
				// + item.getDescription()
				// + " \n last bid : " + " \n user : " +
				// item.getSeller().getId());
				// System.out.println("-------------------");
				// System.out.println();
				//

			}

			// connection.close();

			for (Item currentItem : items) {
				Paths paths = new Paths();
				CompleteItem completeItem = new CompleteItem();
				PreparedStatement statement1;
				String query1 = "select * from path where path.item_id = ?";
				statement1 = connection.prepareStatement(query1);
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
				connection.close();
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
	public void delete(int id, String email) {

		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete i from item i, user u where i.id = ? and i.seller = u.id and u.email = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, id);
			statement.setString(2, email);
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
	}

	@Override
	public void setPath(int item_id, ArrayList<String> paths) {

		Connection connection = this.dataSource.getConnection();
		try {
			for (String path : paths) {

				String insert = "insert into path (item_id, path) values (?,?)";
				PreparedStatement statement = connection.prepareStatement(insert);
				statement.setInt(1, item_id);
				statement.setString(2, path);
				statement.execute();
			}
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

	@Override
	public ArrayList<CompleteItem> getCartPaths(String email) {

		Connection connection = this.dataSource.getConnection();
		ArrayList<CompleteItem> completeItems = new ArrayList<CompleteItem>();
		ArrayList<Item> items = new ArrayList<>();

		try {

			PreparedStatement statement;
			String query = "select item.* from item, user, cart, cartItemsList "
					+ "where user.email = ? && user.id = cart.user_id && cartItemsList.cart_id = cart.id && cartItemsList.item_id = item.id";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				// Item item = new Item();
				// item.setId(result.getInt(1));
				// item.setProducer(result.getString(2));
				// item.setModel(result.getString(3));
				// item.setPrice(result.getFloat(4));
				// item.setTimeToLive(result.getDate(6));
				// item.setDescription(result.getString(7));
				// item.setCategory(new Category(result.getString(8)));
				// item.setBuy_now(result.getBoolean(10));
				// item.setBid(result.getBoolean(11));
				// items.add(item);

				Item item = new Item();
				item.setId(result.getInt(1));
				item.setProducer(result.getString(2));
				item.setModel(result.getString(3));
				item.setPrice(result.getFloat(4));
				item.setLastBid(result.getFloat(5));
				item.setTimeToLive(result.getDate(6));
				item.setDescription(result.getString(7));
				item.setCategory(new Category(result.getString(8)));
				item.setSeller(result.getInt(9));
				item.setBuy_now(result.getBoolean(10));
				item.setBid(result.getBoolean(11));
				items.add(item);

			}

			// connection.close();

			for (Item currentItem : items) {
				Paths paths = new Paths();
				CompleteItem completeItem = new CompleteItem();
				PreparedStatement statement1;
				String query1 = "select * from path where path.item_id = ?";
				statement1 = connection.prepareStatement(query1);
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
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return completeItems;
	}

	@Override
	public ArrayList<CompleteItem> findItems(String searchQuery) {
		Connection connection = this.dataSource.getConnection();
		ArrayList<CompleteItem> completeItems = new ArrayList<CompleteItem>();
		ArrayList<Item> items = new ArrayList<>();
		String[] someQueries = searchQuery.split(" ");
		ArrayList<String> queries = new ArrayList<String>();
		for (String s : someQueries) {
			queries.add(s);
		}
		queries.add(searchQuery);
		try {
			for (String search : queries) {
				PreparedStatement statement;
				String query = "select * from item, category where locate(?, item.producer) or locate(?, item.model) or locate(?, item.description) or locate(?, category.name) ";

				statement = connection.prepareStatement(query);
				statement.setString(1, search);
				statement.setString(2, search);
				statement.setString(3, search);
				statement.setString(4, search);
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
					item.setSeller(result.getInt(9));
					item.setBuy_now(result.getBoolean(10));
					item.setBid(result.getBoolean(11));

					if (!items.contains(item)) {
						items.add(item);
					}
				}
			}

			// connection.close();

			for (Item currentItem : items) {
				Paths paths = new Paths();
				CompleteItem completeItem = new CompleteItem();
				PreparedStatement statement1;
				String query1 = "select * from path where path.item_id = ?";
				statement1 = connection.prepareStatement(query1);
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
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return completeItems;
	}

	@Override
	public void makeBid(int itemToBid) {
		Connection connection = this.dataSource.getConnection();
		try {
			float itemPrice = 0;
			float itemLastBid = 0;
			String select = "select * from item where id = ?";
			PreparedStatement statement = connection.prepareStatement(select);
			statement.setInt(1, itemToBid);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				itemPrice = resultSet.getFloat("price");
				itemLastBid = resultSet.getFloat("lastBid");
			}
			resultSet.close();
			statement.close();

			if (itemLastBid == 0) {

				String update = "update item SET lastBid = ? WHERE id = ?";
				PreparedStatement updateStatement = connection.prepareStatement(update);
				updateStatement.setFloat(1, itemPrice);
				updateStatement.setInt(2, itemToBid);
				updateStatement.executeUpdate();
				updateStatement.close();
			} else if (itemLastBid >= itemPrice) {
				float newBid = (float) (itemLastBid + 0.5);
				String update = "update item SET lastBid = ? WHERE id = ?";
				PreparedStatement updateStatement = connection.prepareStatement(update);
				updateStatement.setFloat(1, newBid);
				updateStatement.setInt(2, itemToBid);
				updateStatement.executeUpdate();
				updateStatement.close();
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

	}

	@Override
	public ArrayList<CompleteItem> findItemsPerCategory(String searchQuery) {

		Connection connection = this.dataSource.getConnection();
		ArrayList<CompleteItem> completeItems = new ArrayList<CompleteItem>();
		ArrayList<Item> items = new ArrayList<>();

		try {
			PreparedStatement statement;
			String query = "select item.* from item, category where category.name = ? && category.id = item.category";

			statement = connection.prepareStatement(query);
			statement.setString(1, searchQuery);

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
				item.setSeller(result.getInt(9));
				item.setBuy_now(result.getBoolean(10));
				item.setBid(result.getBoolean(11));

				if (!items.contains(item)) {
					items.add(item);
				}
			}

			// connection.close();

			for (Item currentItem : items) {
				Paths paths = new Paths();
				CompleteItem completeItem = new CompleteItem();
				PreparedStatement statement1;
				String query1 = "select * from path where path.item_id = ?";
				statement1 = connection.prepareStatement(query1);
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
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return completeItems;
	}

	@Override
	public ArrayList<CompleteItem> findItemsPerProducer(String searchQuery) {
		Connection connection = this.dataSource.getConnection();
		ArrayList<CompleteItem> completeItems = new ArrayList<CompleteItem>();
		ArrayList<Item> items = new ArrayList<>();

		try {
			PreparedStatement statement;
			String query = "select item.* from item where producer = ?";

			statement = connection.prepareStatement(query);
			statement.setString(1, searchQuery);

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
				item.setSeller(result.getInt(9));
				item.setBuy_now(result.getBoolean(10));
				item.setBid(result.getBoolean(11));

				if (!items.contains(item)) {
					items.add(item);
				}
			}

			// connection.close();

			for (Item currentItem : items) {
				Paths paths = new Paths();
				CompleteItem completeItem = new CompleteItem();
				PreparedStatement statement1;
				String query1 = "select * from path where path.item_id = ?";
				statement1 = connection.prepareStatement(query1);
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
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return completeItems;
	}

	@Override
	public ArrayList<String> findAllProducers() {

		Connection connection = this.dataSource.getConnection();
		ArrayList<String> producers = new ArrayList<String>();
		try {
			PreparedStatement statement;
			String query = "select producer from item";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				producers.add(result.getString(1));
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
		return producers;
	}

	@Override
	public ArrayList<CompleteItem> findSomeItems(int nofItems) {
		Connection connection = this.dataSource.getConnection();
		ArrayList<CompleteItem> completeItems = new ArrayList<CompleteItem>();
		ArrayList<Item> items = new ArrayList<>();

		try {
			PreparedStatement statement;
			String query = "select * from item LIMIT ? ";

			statement = connection.prepareStatement(query);
			statement.setInt(1, nofItems);

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
				item.setSeller(result.getInt(9));
				item.setBuy_now(result.getBoolean(10));
				item.setBid(result.getBoolean(11));

				if (!items.contains(item)) {
					items.add(item);
				}
			}

			// connection.close();

			for (Item currentItem : items) {
				Paths paths = new Paths();
				CompleteItem completeItem = new CompleteItem();
				PreparedStatement statement1;
				String query1 = "select * from path where path.item_id = ?";
				statement1 = connection.prepareStatement(query1);
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
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return completeItems;
	}
}
