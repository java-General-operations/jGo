package cloud.jgo.test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cloud.jgo.net.config.Configuration2;
import cloud.jgo.net.config.ServerConfiguration2;
import cloud.jgo.net.config.TCPServerConfiguration2;

public class ConfigTest {
public static void main(String[] args) {
	
	// possiamo inoltre specificare nell'eccezzione
	// quale tipo di valore non è adatto alla chiave
	
	ServerConfiguration2 config2 = new TCPServerConfiguration2();
	
	config2.put(ServerConfiguration2.SERVER_NAME,"Mio server");
	config2.put(ServerConfiguration2.PORT,3333);
	config2.put(TCPServerConfiguration2.MULTI_CONNECTIONS,true);
	config2.put(TCPServerConfiguration2.SERVER_HANDLER_MODEL,new MyTCPHandler());
	
	// a questo punto non ci rimane che implementare questo tipo di configurazione
	// anche nel server
	
}
}
