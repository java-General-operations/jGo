package cloud.jgo.net.tcp.chat.univocal_chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ManageReiceved extends Thread{

	
	private static ObjectInputStream input = null;

	public static ObjectInputStream getInput() {
		return input;
	}
	
	public boolean isRunning() {
		return running;
	}
	public void setRunning(boolean running) {
		this.running = running;
	}

	public static boolean running = false ;
	
	
	// riceve lo stream object gia inizializzato e quindi già collegato alla socket
	public static void setInput(ObjectInputStream in){
		ManageReiceved.input = in ;
	}
public ManageReiceved(Runnable runnable) {
	// TODO Auto-generated constructor stub
	super(runnable);
	// noi subito dopo il costruttore attiviamo il server
	// quindi lo posso fare,setto il running a true
	running = true ;
}
	
	
	
}
