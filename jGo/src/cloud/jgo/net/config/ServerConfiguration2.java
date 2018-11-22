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
		
		
		return null ;
	}
}
