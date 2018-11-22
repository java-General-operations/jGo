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

import cloud.jgo.£;
import cloud.jgo.£Func;
import cloud.jgo.net.config.Configuration2;
import cloud.jgo.net.config.ServerConfiguration2;
import cloud.jgo.net.config.TCPServerConfiguration2;
public class ConfigTest {
public static void main(String[] args) {
	
	// possiamo inoltre specificare nell'eccezzione
	// quale tipo di valore non è adatto alla chiave
	// poi devo fare quel test in jjdom, quindi lavorare su più documenti
	// un altra cosa devo verificare bene il discorso delle istanze
	// di £ e j£,perchè ci può essere il rischio che i metodi di £
	// restituiscono l'istanza di j£, beh devo eliminare questo rischio
	// segnalare che la configurazione in un file.xml deve essere una
	// poichè cosi è convertivile in proprietà
	
	ServerConfiguration2 c = new TCPServerConfiguration2();
	c.put(ServerConfiguration2.LHOST,"localhost");
	c.put(ServerConfiguration2.LPORT,3333);
	c.put(ServerConfiguration2.TIMER,30);
	c.put(ServerConfiguration2.SERVER_NAME,"£http");
	c.put(TCPServerConfiguration2.MULTI_CONNECTIONS,true);
	c.put(TCPServerConfiguration2.ACCEPTED_SOCKET,"connessione ricevuta @");
	c.put(TCPServerConfiguration2.MAXIMUM_SOCKETS,10);
	c.put(TCPServerConfiguration2.HANDLER_MODEL,new MyTCPHandler());
	
	
	
	// continuare scrivendo i metodi per la scrittura in xml
	// e la lettura della stessa
	
}
}
