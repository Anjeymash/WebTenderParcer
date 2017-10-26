package velcomParcer.service.factory;

import velcomParcer.service.TenderService;
import velcomParcer.service.impl.TenderServiceImpl;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();
	
	private final TenderService tenderService = new TenderServiceImpl();

	public static ServiceFactory getInstance() {
		return instance;
	}

		public TenderService getTenderService() {
		return tenderService;
	}
}
