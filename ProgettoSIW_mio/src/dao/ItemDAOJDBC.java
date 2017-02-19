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
			String insert = "insert into item (producer, model, price, ttl, description, category, seller, buy_now, bid ) values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			System.out.println();
			System.out.println("item dao jdbc");
			System.out.println("producer : " + item.getProducer() + " \n model : " + item.getModel()
					+ " \n minimum buy price : " + item.getPrice() + " \n category :  " + item.getCategory().getId()
					+ " \n time : " + item.getTimeToLive() + " \n bid : " + item.isBid() + " \n buy_now : "
					+ item.isBuy_now() + " \n description :  " + item.getDescription() + " \n last bid :  "
					+ " \n user : " + item.getSeller().getId());
			System.out.println("-------------------");
			System.out.println();

			statement.setString(1, item.getProducer());
			statement.setString(2, item.getModel());
			statement.setFloat(3, item.getPrice());
			statement.setDate(4, item.getTimeToLive());
			statement.setString(5, item.getDescription());
			statement.setInt(6, item.getCategory().getId());
			statement.setInt(7, item.getSeller().getId());
			statement.setBoolean(8, item.isBuy_now());
			statement.setBoolean(9, item.isBid());
			// statement.executeUpdate();
			statement.execute();

			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				generatedKey = resultSet.getInt(1);
				System.out.println("chiave generata in item dao jdbc " + generatedKey);
			} else {
				System.out.println("error getting id - ItemDAOJDBC");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			String message = e.getMessage();
			if (message.contains("Duplicate entry")) {
				status = -2;
				System.out.println("duplicate");
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
			String query = "select * from item, user where user.email = ? && item.seller = user.id";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Item item = new Item();
				item.setId(result.getInt(1));
				item.setProducer(result.getString(2));
				item.setModel(result.getString(3));
				item.setPrice(result.getFloat(4));
				item.setTimeToLive(result.getDate(6));
				item.setDescription(result.getString(7));
				item.setCategory(new Category(result.getString(8)));
				item.setBuy_now(result.getBoolean(10));
				item.setBid(result.getBoolean(11));
				items.add(item);
			}

			//connection.close();

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

		// DELETE FROM `progetto_siw`.`user` WHERE `id`='9'
		// and`email`='mariotorritti@gmail.com';

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
				System.out.println("item id: " + item_id + "itemDAOJDBC setPath");
				System.out.println("paths: " + paths.size() + "itemDAOJDBC setPath");
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
}
