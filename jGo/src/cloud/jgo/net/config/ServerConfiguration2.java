package cloud.jgo.net.config;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.ldap.HasControls;

public abstract class ServerConfiguration2 extends Hashtable<String, Object> implements Configuration2{
	public final static String SERVER_NAME = "jgo.net.server.name";
	public final static String SERVER_TYPE = "jgo.net.server.type";
	// available
	protected static List<String>availableConfigurations = new ArrayList<String>();
	static{
		availableConfigurations.add(Configuration2.PORT);
		availableConfigurations.add(Configuration2.TIMER);
		availableConfigurations.add(SERVER_NAME);
		availableConfigurations.add(SERVER_TYPE);
	}
	public static List<String> getAvailableConfigurations() {
		return availableConfigurations;
	}
	@Override
	public synchronized Object put(String key, Object value) {
		Object obj = null ;
		boolean validKey,validValue ;
		validKey = false;validValue = true ;
		for (String config : availableConfigurations) {
			if (config.equals(key)) {
				validKey = true ;
				break ;
			}
		}
		if (validKey) {
			switch(key){
			case SERVER_NAME:
				if (!String.class.isInstance(value)) {
					validValue = false ;
				}
				break ;
			case SERVER_TYPE:
				try {
					throw new ConfigurationNotAccessibleException();
				} catch (ConfigurationNotAccessibleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			if (validValue) {
				obj = super.putIfAbsent(key, value);
			}
			else{
				// qui ci entra solo se il server name non è una stringa
				try {
					throw new InvalidConfigurationException(key);
				} catch (InvalidConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return obj ;
	}
}
