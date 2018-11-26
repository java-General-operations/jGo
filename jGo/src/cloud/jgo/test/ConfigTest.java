package cloud.jgo.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.AllPermission;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.xml.bind.annotation.XmlRootElement;

import cloud.jgo.£;
import cloud.jgo.£Func;
import cloud.jgo.net.config.Configuration2;
import cloud.jgo.net.config.Configuration2.ConfigurationKey;
import cloud.jgo.net.config.ServerConfiguration2;
import cloud.jgo.net.config.ServerConfiguration2.ServerConfigurationKey;
import cloud.jgo.net.config.TCPServerConfiguration2;
import cloud.jgo.net.handlers.Handler;

public class ConfigTest {
public static void main(String[] args) {
	
	// un altra cosa devo verificare bene il discorso delle istanze
	// di £ e j£,perchè ci può essere il rischio che i metodi di £
	// restituiscono l'istanza di j£, beh devo eliminare questo rischio
	// segnalare che la configurazione in un file.xml deve essere una
	// poichè cosi è convertivile in proprietà
	
	
	
	
	ServerConfiguration2 c = new TCPServerConfiguration2("ciao.xml");
	
	System.out.println(c.AllConfigurations());
	
//	c.put(ServerConfiguration2.SERVER_NAME,"My server");
//	c.put(ServerConfiguration2.LPORT,3333);
//	c.put(ServerConfiguration2.TIMER,10);
//	c.put(ServerConfiguration2.LHOST,"localhost");
//	c.put(TCPServerConfiguration2.MULTI_CONNECTIONS,true);
//	c.put(TCPServerConfiguration2.MAXIMUM_SOCKETS,10);
//	c.put(TCPServerConfiguration2.ACCEPTED_SOCKET,"Nuova connessione @");
//	c.put(TCPServerConfiguration2.HANDLER_MODEL,new MyTCPHandler());
//	
//	c.toXML("ciao.xml");
//	System.out.println("Bene");
}
}
