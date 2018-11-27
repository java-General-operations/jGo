package cloud.jgo.net;

import cloud.jgo.net.config.ServerConfiguration;

public final class ServersUtils {
	private ServersUtils() {}
	
	public static boolean compatible(ServerConfiguration config,Server server){
		// prendo la configurazione del server
		ServerConfiguration currentConfig = (ServerConfiguration) server.getConfiguration();
		if (config.getClass().equals(currentConfig.getClass())) {
			return true ;
		}
		else{
		return false ;
		}
	}
}
