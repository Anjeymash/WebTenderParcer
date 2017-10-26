package velcomParcer.service;


import velcomParcer.entity.Tender;
import velcomParcer.service.exception.ServiceException;

public interface TenderService {

Tender parseTender(String num) throws ServiceException ;
void createCSV() throws ServiceException;
void createXML() throws ServiceException;
Tender saveTender() throws ServiceException;
}
