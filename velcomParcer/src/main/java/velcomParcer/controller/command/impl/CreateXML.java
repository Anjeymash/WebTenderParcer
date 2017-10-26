package velcomParcer.controller.command.impl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import velcomParcer.controller.Command;
import velcomParcer.controller.datamanager.JspManager;
import velcomParcer.controller.datamanager.MessageManager;
import velcomParcer.controller.datamanager.ParameterManager;
import velcomParcer.service.TenderService;
import velcomParcer.service.exception.ServiceException;
import velcomParcer.service.factory.ServiceFactory;

public class CreateXML  implements Command {
	private static final Logger log = LogManager.getRootLogger();
		@Override
		public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String page = JspManager.INDEX;
								
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			TenderService tenderService = serviceFactory.getTenderService();

			try {
				tenderService.createXML();
				request.setAttribute(ParameterManager.MES, MessageManager.CREATED_XML);
				
			} catch (ServiceException e) {
				request.setAttribute(ParameterManager.ERROR_MES, e.getMessage());
				log.error("error in CreateXML", e);
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);

		}
	}
