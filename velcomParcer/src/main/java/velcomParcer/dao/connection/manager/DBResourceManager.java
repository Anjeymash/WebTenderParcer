package velcomParcer.dao.connection.manager;

import java.util.ResourceBundle;

public final class DBResourceManager {
	private static DBResourceManager instance = null;
	private final ResourceBundle bundle = ResourceBundle.getBundle("database");
	
	private DBResourceManager() {}

	public static DBResourceManager getInstance() {
		if(instance == null){
			instance = new DBResourceManager();
		}
		return instance;
	}
	
	public String getValue(String key){
		return bundle.getString(key);
	}
	
}
