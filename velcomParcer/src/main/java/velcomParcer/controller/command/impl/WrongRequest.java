package velcomParcer.controller.command.impl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import velcomParcer.controller.Command;
import velcomParcer.controller.datamanager.JspManager;
import velcomParcer.controller.datamanager.MessageManager;
import velcomParcer.controller.datamanager.ParameterManager;


public class WrongRequest implements Command {

	RequestDispatcher dispatcher;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String page = JspManager.INDEX;
		dispatcher = request.getRequestDispatcher(page);
		request.setAttribute(ParameterManager.ERROR_MES , MessageManager.WRONG);
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);

	}

}
