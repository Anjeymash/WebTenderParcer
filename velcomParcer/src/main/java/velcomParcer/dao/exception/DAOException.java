package velcomParcer.dao.exception;

public class DAOException extends Exception {
	private static final long serialVersionUID = 1L;

	public DAOException() {
		super();
	}

	public DAOException(String mes) {
		super(mes);
	}

	public DAOException(Exception e) {
		super(e);
	}

	public DAOException(String mes, Exception e) {
		super(mes, e);
	}

}