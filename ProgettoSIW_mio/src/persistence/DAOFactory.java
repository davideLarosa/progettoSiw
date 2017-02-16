package persistence;

import dao.CategoryDAO;
import dao.ItemDAO;
import dao.MailingListDAO;
import dao.UserDAO;

public abstract class DAOFactory {

	// --- List of supported DAO types ---

	
	/**
	 * Numeric constant '1' corresponds to explicit MYSQL choice
	 */
	public static final int MYSQL = 1;
	
	/**
	 * Numeric constant '2' corresponds to explicit Postgres choice
	 */
	public static final int POSTGRESQL = 2;
	
	
	// --- Actual factory method ---
	
	/**
	 * Depending on the input parameter
	 * this method returns one out of several possible 
	 * implementations of this factory spec 
	 */
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch ( whichFactory ) {
		
		case MYSQL:
			return null;//new HsqldbDAOFactory();
		case POSTGRESQL:
			return null; //new PostgresDAOFactory();
		default:
			return null;
		}
	}
	
	public abstract UserDAO getUserDAO();
	
	public abstract MailingListDAO getMailingListDAO();
	
	public abstract ItemDAO getItemDAO();
	
	public abstract CategoryDAO getCategoryDAO();
	
//	public abstract MessaggioDAO getMessaggioDAO();
//	
//	public abstract TelefonoDAO getTelefonoDAO();

//	public abstract persistence.UtilDao getUtilDAO();

}
