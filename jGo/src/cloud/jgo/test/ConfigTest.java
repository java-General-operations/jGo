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
import cloud.jgo.net.config.Configuration;
import cloud.jgo.net.config.Configuration.ConfigurationKey;
import cloud.jgo.net.config.HTTPServerConfiguration;
import cloud.jgo.net.config.ServerConfiguration2;
import cloud.jgo.net.config.TCPLoginServerConfiguration;
import cloud.jgo.net.config.TCPServerConfiguration;
import cloud.jgo.net.handlers.Handler;

public class ConfigTest {
public static void main(String[] args) {
	
	// bene , se proviamo a memorizzare una configurazione + avanzata
	// in un oggetto non compatibile per esmepipo con una configurazione
	// dei tcp server login, proviamo ad aquisirne un oggetto di configurazione
	// normale TCP, verranno aquisiti solo i tag compatibili con quella
	// configurazione.
	// un altra cosa devo verificare bene il discorso delle istanze
	// di £ e j£,perchè ci può essere il rischio che i metodi di £
	// restituiscono l'istanza di j£, beh devo eliminare questo rischio
	// segnalare che la configurazione in un file.xml deve essere una
	// poichè cosi è convertivile in proprietà

	ServerConfiguration2 c = new TCPServerConfiguration();

	c.put(ServerConfiguration2.LHOST,"localhost");
	c.put(ServerConfiguration2.LPORT, 3332);
	c.put(ServerConfiguration2.SERVER_NAME,"Mio server");
	c.put(ServerConfiguration2.TIMER,100);
	c.put(TCPServerConfiguration.MULTI_CONNECTIONS,true);
	c.put(TCPServerConfiguration.MAXIMUM_SOCKETS,10);
	c.put(TCPServerConfiguration.DEFAULT_PRINT_FOR_ACCEPTANCE_SOCKET,true);
	c.put(TCPServerConfiguration.ACCEPTED_SOCKET,"Connessione in entrata @");
	c.put(TCPServerConfiguration.HANDLER_MODEL,new MyTCPHandler());
	c.put(TCPLoginServerConfiguration.ATTEMPTS, 3);
	

	System.out.println("Configurazione avvenuta @");
	
	
	
}
}
