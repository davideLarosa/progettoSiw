package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Email;
import persistence.DataSource;

public class MailingListDAOJDBC implements MailingListDAO {

	private DataSource dataSource;

	public MailingListDAOJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int save(Email email) {
		Connection connection = this.dataSource.getConnection();
		String insert = "insert into mailingList(email) values(?)";
		int status = -1;
		try {
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(insert);
			statement.setString(1, email.getAddress());
			statement.executeUpdate();
			status = 0;
		} catch (SQLException e) {
			String message = e.getMessage();
			if (message.contains("Duplicate entry '" + email.getAddress() + "' for key 'email_UNIQUE'")) {
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
	public Email findByPrimaryKey(Email email) {
		Connection connection = this.dataSource.getConnection();
		Email dbData = null;
		try {
			PreparedStatement statement;
			String query = "select * from mailingList where email=?";
			statement = (PreparedStatement) connection.prepareStatement(query);
			statement.setString(1, email.getAddress());
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				dbData = new Email();
				dbData.setId(results.getInt("id"));
				dbData.setAddress(results.getString("email"));
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
		return dbData;
	}

	@Override
	public List<Email> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Email email) {
		// TODO Auto-generated method stub

	}

}
