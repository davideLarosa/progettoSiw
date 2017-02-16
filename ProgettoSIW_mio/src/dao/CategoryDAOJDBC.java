package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import persistence.DataSource;
import persistence.PersistenceException;

public class CategoryDAOJDBC implements CategoryDAO {

	private DataSource dataSource;

	public CategoryDAOJDBC(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public Category findByPrimaryKey(String categoryName) {
		Connection connection = this.dataSource.getConnection();
		Category category = null;
		try {
			PreparedStatement statement;
			String query = "select * from category where name=?";
			statement = (PreparedStatement) connection.prepareStatement(query);
			statement.setString(1, categoryName);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				category = new Category();
				category.setId(results.getInt("id"));
				category.setName(results.getString("name"));
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
		return category;
	}

	@Override
	public List<Category> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Category> categories = new ArrayList<>();
		try {
			Category category = null;
			PreparedStatement statement;
			String query = "select * from category";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				category = new Category();
				category.setId(result.getInt("id"));
				category.setName(result.getString("name"));
				categories.add(category);
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
		return categories;
	}

}
