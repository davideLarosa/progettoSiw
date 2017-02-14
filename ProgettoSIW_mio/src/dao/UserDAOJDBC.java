package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;

import model.User;
import persistence.DataSource;

public class UserDAOJDBC implements UserDAO {

	private DataSource dataSource;

	public UserDAOJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int save(User user) {
		Connection connection = this.dataSource.getConnection();
		String insert = "insert into user(name, surname, email, phone, address, password, seller) values(?,?,?,?,?,?,?)";
		int status = -1;
		try {
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(insert);
			statement.setString(1, user.getName());
			statement.setString(2, user.getSurname());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPhone());
			statement.setString(5, user.getAddress());
			statement.setString(6, user.getPassword());
			statement.setBoolean(7, user.isSeller());
			statement.executeUpdate();
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
				user.setName(results.getString("name"));
				user.setSurname(results.getString("surname"));
				user.setEmail(results.getString("email"));
				user.setPassword(results.getString("password"));
			}

		} catch (Exception e) {
			return null;
			// throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				return null;
				// throw new PersistenceException(e.getMessage());
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
				user.setName(results.getString("name"));
				user.setSurname(results.getString("surname"));
				user.setEmail(results.getString("email"));
				user.setPhone(results.getString("phone"));
				user.setAddress(results.getString("address"));
				user.setPassword(results.getString("password"));
				user.setSeller(results.getBoolean("seller"));
			}

		} catch (Exception e) {
			return null;
			// throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				return null;
				// throw new PersistenceException(e.getMessage());
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
				user.setSeller(results.getBoolean("seller"));
				user.setId(results.getInt("id"));
			}

		} catch (Exception e) {
			// TODO
			// throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO
				// throw new PersistenceException(e.getMessage());
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

		String update = "UPDATE user SET name=?, surname=?, phone=?, address=?, password=?, seller=? WHERE email=\""
				+ newData.getEmail() + "\"";

		try {
			PreparedStatement statement = (PreparedStatement) newConnection.prepareStatement(update);
			statement.setString(1, newData.getName());
			statement.setString(2, newData.getSurname());
			statement.setString(3, newData.getPhone());
			statement.setString(4, newData.getAddress());
			statement.setString(5, newData.getPassword());
			statement.setBoolean(6, newData.isSeller());
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
}
