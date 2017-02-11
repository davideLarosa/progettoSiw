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
			if(!connection.isClosed())
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
	public void update(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub

	}

}
