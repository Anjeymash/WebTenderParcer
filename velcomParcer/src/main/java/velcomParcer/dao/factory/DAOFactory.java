package velcomParcer.dao.factory;

import velcomParcer.dao.TenderDAO;
import velcomParcer.dao.impl.SQLTenderDAO;

public class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();
	private final TenderDAO sqlTenderImpl = new SQLTenderDAO();
	

	public static DAOFactory getInstance() {
		return instance;
	}

	public TenderDAO getTenderDAO() {
		return sqlTenderImpl;
	}

}
