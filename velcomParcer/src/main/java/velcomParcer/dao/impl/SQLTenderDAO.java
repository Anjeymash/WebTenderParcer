package velcomParcer.dao.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import velcomParcer.dao.TenderDAO;
import velcomParcer.dao.connection.ConnectionPool;
import velcomParcer.dao.connection.exception.ConnectionPoolException;
import velcomParcer.dao.exception.DAOException;
import velcomParcer.entity.Tender;

public class SQLTenderDAO implements TenderDAO {
	private static final Logger log = LogManager.getRootLogger();
	private static final String INSERT_TENDER = "INSERT INTO tender (t_title, t_industry, t_contacts, t_email, t_start, t_end) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SELECT_ID = "select last_insert_id() as last_id from tender";
	private ConnectionPool conPool = ConnectionPool.getInstance();
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String FILE_PATH = "C:/JD2/Workspace/velcomParcer/src/main/resources/";
	private Long id;

	@Override
	public Long saveTender(Tender tender) throws DAOException {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		id = 0L;
		try {
			con = conPool.takeConnection();
			ps = con.prepareStatement(INSERT_TENDER);
			ps.setString(1, tender.getTitle());
			ps.setString(2, tender.getIndustry());
			ps.setString(3, tender.getContacts());
			ps.setString(4, tender.geteMail());
			ps.setString(5, tender.getStart());
			ps.setString(6, tender.getEnd());

			ps.executeUpdate();
			st = con.createStatement();
			rs = st.executeQuery(SELECT_ID);
			while (rs.next())
				id = rs.getLong("last_id");

		} catch (SQLException e) {
			log.error("error in SaveTender", e);
			throw new DAOException("SQL error", e);
			

		} catch (ConnectionPoolException e) {
			log.error("error in SaveTender", e);
			throw new DAOException("SQL connection error", e);
		}

		finally {
			conPool.closeConnection(con, ps, rs);

		}
		return id;
	}

	@SuppressWarnings("resource")
	@Override
	public void createXML(Document doc) throws DAOException {
		BufferedWriter htmlWriter;
		try {
			String filePath = FILE_PATH + "xml/" + id.toString() + ".xml";
			htmlWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));
			htmlWriter.write(doc.outerHtml());
			System.out.println("creating " + doc.select("tr.af-created td.afv").text());
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			log.error("error in CreateXML", e);
			throw new DAOException("xml-error", e);
		}

		catch (IOException e) {
			log.error("error in CreateXML", e);
			throw new DAOException("xml-error", e);
			
		}
	}

	@Override
	public void createCSV(Tender tender) throws DAOException {
		PrintWriter pw;
		try {
			String filePath = FILE_PATH + "csv/" + id.toString() + ".csv";
			pw = new PrintWriter(new File(filePath));
			StringBuilder sb = new StringBuilder();
			sb.append("id");
			sb.append(COMMA_DELIMITER);
			sb.append(tender.getId());
			sb.append(NEW_LINE_SEPARATOR);
			sb.append("title");
			sb.append(COMMA_DELIMITER);
			sb.append(tender.getTitle());
			sb.append(NEW_LINE_SEPARATOR);
			sb.append("industry");
			sb.append(COMMA_DELIMITER);
			sb.append(tender.getIndustry());
			sb.append(NEW_LINE_SEPARATOR);
			sb.append("contacts");
			sb.append(COMMA_DELIMITER);
			sb.append(tender.getContacts());
			sb.append(NEW_LINE_SEPARATOR);
			sb.append("e-mail");
			sb.append(COMMA_DELIMITER);
			sb.append(tender.geteMail());
			sb.append(NEW_LINE_SEPARATOR);
			sb.append("start");
			sb.append(COMMA_DELIMITER);
			sb.append(tender.getStart());
			sb.append(NEW_LINE_SEPARATOR);
			sb.append("end");
			sb.append(COMMA_DELIMITER);
			sb.append(tender.getEnd());

			pw.write(sb.toString());
			pw.close();
		} catch (FileNotFoundException e) {
			log.error("error in createCSV", e);
			throw new DAOException("CSV-error", e);
		}

	}
}
