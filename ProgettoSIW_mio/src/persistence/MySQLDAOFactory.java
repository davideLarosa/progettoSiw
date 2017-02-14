package persistence;

import dao.MailingListDAO;
import dao.MailingListDAOJDBC;
import dao.UserDAO;
import dao.UserDAOJDBC;

public class MySQLDAOFactory extends DAOFactory {
	private DataSource dataSource;

	public MySQLDAOFactory(String host, String databaseName, String port, String user, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			// questi vanno messi in file di configurazione!!!
			dataSource = new DataSource("jdbc:mysql://" + host + ":" + port + "/" + databaseName, user, password);
		} catch (Exception e) {
			System.err.println("MYSQLDAOFactory.class: failed to load MySQL JDBC driver\n" + e);
			e.printStackTrace();
		}
	}

	@Override
	public UserDAO getUserDAO() {
		return new UserDAOJDBC(this.dataSource);
	}

	@Override
	public MailingListDAO getMailingListDAO() {
		return new MailingListDAOJDBC(this.dataSource);
	}

	// @Override
	// public StudenteDao getStudentDAO() {
	// return new StudenteDaoJDBC(dataSource);
	// }
	//
	// @Override
	// public GruppoDao getGruppoDAO() {
	// return new GruppoDaoJDBC(dataSource);
	// }
	// @Override
	// public UtilDao getUtilDAO(){
	// return new UtilDao(dataSource);
	// }
}
