package velcomParcer.service.impl;


import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import velcomParcer.dao.TenderDAO;
import velcomParcer.dao.exception.DAOException;
import velcomParcer.dao.factory.DAOFactory;
import velcomParcer.entity.Tender;
import velcomParcer.service.TenderService;
import velcomParcer.service.exception.ServiceException;

public class TenderServiceImpl implements TenderService {
	private static final Logger log = LogManager.getRootLogger();
	private static final Pattern EMAIL = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+");
	private static final String TENDER_URL = "http://www.icetrade.by/tenders/all/view/";
	private static final String ST_D = "tr.af-created td.afv";
	private static final String EN_D = "tr.af-request_end td.afv";
	private static final String CONT = "tr.af-customer_contacts td.afv";
	private static final String IND = "tr.af-industry td.afv";
	private static final String TTL = "tr.af-title td.afv";
	private Tender tender;
	private Document doc;
	@Override
	public Tender saveTender() throws ServiceException {
		Long id = 0L;

		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			TenderDAO tenderDAO = daoObjectFactory.getTenderDAO();
					
			if ((tender.getTitle()==null)||(tender.getTitle().isEmpty())||(tender == null)) {
				throw new ServiceException("tender doesn't exist");}
			else
			{id = tenderDAO.saveTender(tender);
			tender.setId(id);}

		} catch (DAOException e) {
			log.error("error in SaveTender", e);
			throw new ServiceException("SQL connection error", e);
			

		}
		return tender;

	}
	@Override
	public Tender parseTender(String num) throws ServiceException {
		tender = new Tender();

		try {
			doc = Jsoup.connect(TENDER_URL + num).get();
			String startDate = doc.select(ST_D).text();
			String endDate = doc.select(EN_D).text();
			String contacts = doc.select(CONT).text();
			String eMail = null;
			Matcher m = EMAIL.matcher(contacts);
			while (m.find()) {
				eMail = m.group();
			}
			String industry = doc.select(IND).text();
			String title = doc.select(TTL).text();
			tender.setContacts(contacts);
			tender.setTitle(title);
			tender.seteMail(eMail);
			tender.setIndustry(industry);
			tender.setStart(startDate);
			tender.setEnd(endDate);
						
		} catch (IOException e) {
			log.error("error in parceTender", e);
			throw new ServiceException("parsing error", e);
			
		}
		if ((tender.getTitle()==null)||(tender.getTitle().isEmpty())||(tender == null)) {
			throw new ServiceException("tender doesn't exist");}
		return tender;

	}
	@Override

	public void createXML() throws ServiceException {
					
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			TenderDAO tenderDAO = daoObjectFactory.getTenderDAO();
			try {
				tenderDAO.createXML(doc);
			} catch (DAOException e) {
				log.error("error in createXML", e);
				throw new ServiceException("XML-file con not be created", e);
			
			}
}
	@Override

	public void createCSV() throws ServiceException {
					
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			TenderDAO tenderDAO = daoObjectFactory.getTenderDAO();
			try {
				tenderDAO.createCSV(tender);
			} catch (DAOException e) {
				log.error("error in createCSV", e);
				throw new ServiceException("CSV-file con not be created", e);
			}
}
	
	
	
}
