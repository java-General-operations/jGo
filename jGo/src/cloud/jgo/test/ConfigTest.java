package cloud.jgo.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
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
public static void main(String[] args) throws SocketException {
	
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

	// adesso mi creo una configurazione con cui creare il server 
	
	
	// ecco come creare un server configurato dall'esterno
	
	TCPServer server = TCPServer.creates(new TCPServerConfiguration("config.xml"));
	
	System.out.println(server.getConfiguration().AllConfigurations());
	
	
    
	
}
}
