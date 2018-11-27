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
import cloud.jgo.net.config.ServerConfiguration;
import cloud.jgo.net.factorys.ServersFactory;
import cloud.jgo.net.handlers.Handler;
import cloud.jgo.net.tcp.TCPServer;
import cloud.jgo.net.tcp.TCPServerConfiguration;
import cloud.jgo.net.tcp.TCPServerTypes;
import cloud.jgo.net.tcp.http.HTTPServerConfiguration;
import cloud.jgo.net.tcp.login.TCPLoginServerConfiguration;

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

	TCPServer server = (TCPServer) ServersFactory.getInstance().createServer(TCPServerTypes.TYPE_TCP.VALUE,3332);
	
	server.getConfiguration().put(TCPServerConfiguration.MULTI_CONNECTIONS,true);
	server.getConfiguration().put(TCPServerConfiguration.SERVER_NAME,"Mio server");
	server.getConfiguration().put(TCPServerConfiguration.TIMER,100);
	
	server.reloadConfiguration();
	
	
	
}
}
