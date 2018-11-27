package cloud.jgo.net;

import cloud.jgo.net.config.ServerConfiguration;
import cloud.jgo.net.handlers.Handler;

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
	public static boolean compatible(Handler handler,Server server){
		
		return false ;
	}
}
