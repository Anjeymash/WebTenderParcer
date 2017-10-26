package velcomParcer.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import velcomParcer.dao.connection.ConnectionPool;
import velcomParcer.dao.connection.exception.ConnectionPoolException;


public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final CommandProvider provider = new CommandProvider();

	public Controller() {
		super();
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		ConnectionPool conPool = ConnectionPool.getInstance();
		try {
			conPool.initPoolData();
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String commandName = request.getParameter("command");

		Command command = provider.getCommand(commandName);

		command.execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String commandName = request.getParameter("command");

		Command command = provider.getCommand(commandName);

		command.execute(request, response);
	}

	public void destroy() {
		ConnectionPool conPool = ConnectionPool.getInstance();
		conPool.dispose();
	}
}
