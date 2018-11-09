package cloud.jgo.net.tcp.chat;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.management.MemoryManagerMXBean;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JOptionPane;

import cloud.jgo.£;
import cloud.jgo.net.tcp.TCPHandlerConnection;

public class ChatHandlerConnection extends TCPHandlerConnection{
	
	private int typeNotifier;
	private ChatServer server = null ;
	// Ora mettiamo un po da parte la chat e pensiam al model

	public ChatHandlerConnection(Socket socket,int typeNotifier) throws IOException {
		setSocket(socket); 
		this.typeNotifier = typeNotifier ;
		// qui appena prendo la socket gli mand lo stato attuale della chat
		String currentStateChat =ChatServer.getChat().toString();
		
		send(currentStateChat);
	}
	
	public ChatHandlerConnection(Socket socket,ChatServer server) throws IOException {
		setSocket(socket); 
		// qui di default impostiamo il notify su udp notifier
		this.typeNotifier = NotifierServerSide.TYPE_UDP ;
		this.server = server ;
		// qui appena prendo la socket gli mand lo stato attuale della chat
		String currentStateChat =ChatServer.getChat().toString();
		
		send(currentStateChat);
		
	}
	
	/*
	 * questo costruttore non prevede un impostazione della socket 
	 * ma si limita a usare la socket tramite i suoi streams
	 */
	public ChatHandlerConnection(ObjectOutputStream out, ObjectInputStream in) throws IOException {
		super(out, in);
		this.typeNotifier = NotifierServerSide.TYPE_UDP ;
		// qui appena prendo l stream di out  mand lo stato attuale della chat
				String currentStateChat =ChatServer.getChat().toString();
				
				out.writeObject(currentStateChat);
	}
	
	public ChatHandlerConnection(){
		// void
	}
	
	@Override
	public void manage() throws ClassNotFoundException, IOException {
		while(true){
			Mexg mexFromClient = (Mexg) receive();
		    if(!mexFromClient.getMex().equals("")&&mexFromClient.getMex()!=null&&!mexFromClient.equals("\n")){
			if(!mexFromClient.getMex().equals("close")){
				ChatServer.getChat().add(mexFromClient);	
			}
		    	
			}
			
			NotifierServerSide notifier = new NotifierServerSide(this.server);
			notifier.setType(this.typeNotifier);
			notifier.start(); // parte il metodo notification()
			if(mexFromClient.getMex().equals("close")){
				// esco semplicemente dal ciclo
				break ;
			}
		}	
	}
	
}
