package velcomParcer.dao;

import org.jsoup.nodes.Document;

import velcomParcer.dao.exception.DAOException;
import velcomParcer.entity.Tender;

public interface TenderDAO {

	Long saveTender(Tender tender) throws DAOException;

	void createXML(Document doc) throws DAOException;

	void createCSV(Tender tender) throws DAOException;

}