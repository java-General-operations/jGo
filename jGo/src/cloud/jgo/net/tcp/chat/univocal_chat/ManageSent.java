package cloud.jgo.net.tcp.chat.univocal_chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ManageSent extends Thread{

	private static ObjectOutputStream output = null;

	public static ObjectOutputStream getOutput() {
		return output;
	}

	public static void setOutput(ObjectOutputStream output) {
		ManageSent.output = output;
	}
	
	public ManageSent(Runnable runnable) {
		// TODO Auto-generated constructor stub
		super(runnable);
		running = true ;
	}
	
	public static boolean running = false ;
	
	

	
	
}
