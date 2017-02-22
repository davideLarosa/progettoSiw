package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.api.jdbc.Statement;

import model.CompleteItem;
import model.User;
import persistence.DBManager;
import persistence.DataSource;
import persistence.PersistenceException;

public class UserDAOJDBC implements UserDAO {

	private DataSource dataSource;

	public UserDAOJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int save(User user) {
		Connection connection = this.dataSource.getConnection();
		String insert = "insert into user(name, surname, email, phone, address, password) values(?,?,?,?,?,?)";
		int status = -1;
		try {
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(insert,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getName());
			statement.setString(2, user.getSurname());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPhone());
			statement.setString(5, user.getAddress());
			statement.setString(6, user.getPassword());
			statement.execute();

			ResultSet resultSet = statement.getGeneratedKeys();
			while (resultSet.next()) {
				user.setId(resultSet.getInt(1));
			}
			status = 0;
		} catch (SQLException e) {
			String message = e.getMessage();
			if (message.contains("Duplicate entry '" + user.getEmail() + "' for key 'email_UNIQUE'")) {
				status = 1;
			}
			// throw new RuntimeException(messatmp);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				status = 2;
				e.printStackTrace();
				// throw new RuntimeException(e.getMessage());
			}
		}

		return status;
	}

	@Override
	public User findByPrimaryKey(String email, String password) {
		Connection connection = this.dataSource.getConnection();
		User user = null;
		try {
			PreparedStatement statement;
			String query = "select * from user where email=? && password=?";
			statement = (PreparedStatement) connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				user = new User();
				user.setId(results.getInt("id"));
				System.out.println("in user dao jd id è " + user.getId());
				user.setName(results.getString("name"));
				user.setSurname(results.getString("surname"));
				user.setEmail(results.getString("email"));
				user.setPassword(results.getString("password"));
			}

		} catch (Exception e) {
			// return null;
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// return null;
				throw new PersistenceException(e.getMessage());
			}
		}
		try {
			if (!connection.isClosed())
				connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User findByPrimaryKey(String email) {
		Connection connection = this.dataSource.getConnection();
		User user = null;
		try {
			PreparedStatement statement;
			String query = "select * from user where email=?";
			statement = (PreparedStatement) connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				user = new User();
				user.setId(results.getInt("id"));
				user.setName(results.getString("name"));
				user.setSurname(results.getString("surname"));
				user.setEmail(results.getString("email"));
				user.setPhone(results.getString("phone"));
				user.setAddress(results.getString("address"));
				user.setPassword(results.getString("password"));
			}

		} catch (Exception e) {
			// return null;
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// return null;
				throw new PersistenceException(e.getMessage());
			}
		}
		try {
			if (!connection.isClosed())
				connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User newData) {
		Connection connection = this.dataSource.getConnection();
		String email = newData.getEmail();
		User user = null;
		try {
			PreparedStatement statement;
			String query = "select * from user where email=?";
			statement = (PreparedStatement) connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				user = new User();
				user.setName(results.getString("name"));
				user.setSurname(results.getString("surname"));
				user.setEmail(results.getString("email"));
				user.setPhone(results.getString("phone"));
				user.setAddress(results.getString("address"));
				user.setPassword(results.getString("password"));
				user.setId(results.getInt("id"));
			}

		} catch (Exception e) {
			// TODO
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO
				throw new PersistenceException(e.getMessage());
			}
		}
		try {
			if (!connection.isClosed())
				connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Connection newConnection = this.dataSource.getConnection();

		String update = "UPDATE user SET name=?, surname=?, phone=?, address=?, password=? WHERE email=?";

		try {
			PreparedStatement statement = (PreparedStatement) newConnection.prepareStatement(update);
			statement.setString(1, newData.getName());
			statement.setString(2, newData.getSurname());
			statement.setString(3, newData.getPhone());
			statement.setString(4, newData.getAddress());
			statement.setString(5, newData.getPassword());
			statement.setString(6, newData.getEmail());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				newConnection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}

		try {
			if (!newConnection.isClosed()) {
				newConnection.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub

	}

	// @Override
	// public Cart getCart(String email) {
	// System.out.println(email);
	// Connection connection = this.dataSource.getConnection();
	// Cart cart = new Cart();
	//
	// try {
	// PreparedStatement statement;
	// String getCart = "select cartItemsList.item_id from cartItemsList, cart,
	// user "
	// + "where user.email = ? && user.id = cart.user_id &&
	// cartItemsList.cart_id = cart.id";
	// statement = connection.prepareStatement(getCart);
	// statement.setString(1, email);
	// ResultSet result = statement.executeQuery();
	//
	// while (result.next()) {
	// cart.addItem(result.getInt("item_id"));
	// }
	//
	// // result.close();
	//
	// System.out.println("cartId " + cart.getId());
	// System.out.println("userId " + cart.getUserId());
	// for (Integer i : cart.getItems()) {
	// System.out.println("item id: " + i);
	// }
	//
	// } catch (SQLException e) {
	// throw new PersistenceException(e.getMessage());
	// } finally {
	// try {
	// connection.close();
	// } catch (SQLException e) {
	// throw new PersistenceException(e.getMessage());
	// }
	// }
	// return cart;
	// }

	// @Override
	// public int getCartId(String email) {
	// Connection connection = this.dataSource.getConnection();
	// int cartId = 0;
	// try {
	// PreparedStatement statement;
	// String query = "select cart.id from cart where cart.user_id = user.id &&
	// user.email = ?";
	// statement = connection.prepareStatement(query);
	// statement.setString(1, email);
	// ResultSet result = statement.executeQuery();
	// if (result.next()) {
	// cartId = result.getInt("id");
	// }
	// } catch (SQLException e) {
	// throw new PersistenceException(e.getMessage());
	// } finally {
	// try {
	// connection.close();
	// } catch (SQLException e) {
	// throw new PersistenceException(e.getMessage());
	// }
	// }
	// return cartId;
	// }

	@Override
	public int addToCart(String email, int item_id) {
		Connection connection = this.dataSource.getConnection();
		int user_id = 0;
		int cart_id = 0;
		int status = -1;
		try {
			String getUserId = "select id from user where email=?";
			PreparedStatement getUser_id = connection.prepareStatement(getUserId);
			getUser_id.setString(1, email);
			ResultSet resultSet = getUser_id.executeQuery();
			if (resultSet.next()) {
				user_id = resultSet.getInt(1);
			}

			String getCartId = "select id from cart where user_id = ?";
			PreparedStatement statement = connection.prepareStatement(getCartId);
			statement.setInt(1, user_id);

			ResultSet resultSet2 = statement.executeQuery();

			if (resultSet2.next()) {
				cart_id = resultSet2.getInt(1);
			}

			String getCartItemsList = "select * from cartItemsList where cart_id = ? && item_id =?";
			PreparedStatement getCartItemsListStatement = connection.prepareStatement(getCartItemsList);
			getCartItemsListStatement.setInt(1, cart_id);
			getCartItemsListStatement.setInt(2, item_id);
			ResultSet resultSet3 = getCartItemsListStatement.executeQuery();

			int cartItemsListID = 0;
			while (resultSet3.next()) {
				cartItemsListID = resultSet.getInt(1);
			}
			if (cartItemsListID == 0) {
				addInCart(cart_id, item_id);
			} 

			System.out.println("aggiunto");
			status = 0;
		} catch (SQLException e) {
			String message = e.getMessage();
			if (message.contains("Duplicate entry")) {
				status = 1;
				System.out.println("doppio");
			}
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				status = 2;
				throw new PersistenceException(e.getMessage());
			}
		}
		return status;
	}

	@Override
	public ArrayList<CompleteItem> getCartPaths(String email) {
		return DBManager.getInstance().getItemDAO().getCartPaths(email);
	}

	@Override
	public void removeFromCart(String email, int item_id) {

		Connection connection = this.dataSource.getConnection();
		try {
			String select = "delete cartItemsList from user, cart, cartItemsList "
					+ "where user.email = ? && cart.user_id = user.id && cartItemsList.cart_id = cart.id && cartItemsList.item_id = ?";

			PreparedStatement statement = connection.prepareStatement(select);
			statement.setString(1, email);
			statement.setInt(2, item_id);
			statement.execute();

			System.out.println("eliminato");
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
	public int getUserID(String email) {

		int id = 0;
		Connection connection = this.dataSource.getConnection();
		try {
			String select = "select id from user where email=?";

			PreparedStatement statement = connection.prepareStatement(select);
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				resultSet.getInt(1);
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
		return id;
	}

	private void addInCart(int cart_id, int item_id) {
		Connection connection = this.dataSource.getConnection();

		try {
			String insert = "insert into cartItemsList (cart_id, item_id) values(?,?)";
			PreparedStatement statement4 = connection.prepareStatement(insert);
			statement4.setInt(1, cart_id);
			statement4.setInt(2, item_id);
			statement4.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
