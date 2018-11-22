package cloud.jgo.net.config;

import java.util.Hashtable;
import java.util.logging.Logger;

import javax.naming.ldap.HasControls;

public abstract class ServerConfiguration2 extends Hashtable<String, Object> implements Configuration2{
	public final static String SERVER_NAME = "jgo.net.server.name";
	public final static String SERVER_TYPE = "jgo.net.server.type";
	// available
	protected static String[]availableConfigurations = 
	{Configuration2.PORT,Configuration2.TIMER,SERVER_NAME,SERVER_TYPE};


	public static String[] getAvailableConfigurations() {
		return availableConfigurations;
	}
	
	@Override
	public synchronized Object put(String key, Object value) {
		Object obj = null ;
		boolean valid = false ;
		for (int i = 0; i < availableConfigurations.length; i++) {
			if (key.equals(availableConfigurations[i])) {
				valid = true ;
			}
		}
		if (valid) {
			// si procede si controlla il tipo
			switch(key){
			
			case Configuration2.PORT:
				if (!Integer.class.isInstance(value)) {
					valid = false;
				}
				break ;
			case Configuration2.TIMER:
				if (!Integer.class.isInstance(value)) {
					valid = false;
				}
				break ;
				
			case SERVER_NAME:
				if (!String.class.isInstance(value)) {
					valid = false ;
				}
				break ;
				
			case SERVER_TYPE:
				
				// dare una eccezzione
				
				break ;
			}
			if (valid) {
				obj = super.put(key, value);
			}
		}
		return obj ;
	}
}
