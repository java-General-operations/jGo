package cloud.jgo.net.tcp.chat;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import cloud.jgo.net.tcp.NoReadingSourceException;

public class NotifierServerSide extends Thread{

	
	public static final int TYPE_TCP =0;
	public static final int TYPE_UDP =1 ;
	private int type ;
	private DatagramSocket udp_socket = null ;
	private Socket tcp_socket = null ;
	private ChatServer server;
	
	
	public NotifierServerSide(ChatServer server) {
		// TODO Auto-generated constructor stub
		this.server = server ;
	}

	// questo notificatore deve mandare lo stato della chat a tutti gli utenti
	public void setType(int typeNotifier){
		this.type = typeNotifier ;
		
	}
	
	@Override
	public void run() {
		try {
			try {
				notification();
			} catch (NoReadingSourceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// questo è il metodo che viene chiamato nel thread
	public void notification() throws IOException, NoReadingSourceException{
		
      switch(this.type){
		
		case TYPE_TCP:
			
			break ;
		case TYPE_UDP:
			Iterator<Socket>socketsIterator = ChatServer.getSockets().iterator();
			while(socketsIterator.hasNext()){
				InetAddress address = socketsIterator.next().getInetAddress();
				this.udp_socket = new DatagramSocket();
				// preparo il pacchetto
				ByteArrayOutputStream fos = new ByteArrayOutputStream();
				DataOutputStream out = new DataOutputStream(fos);
				out.writeUTF(ChatServer.getChat().toString());
				byte[]buffer = fos.toByteArray();
				out.flush();out.close();
				fos.close();fos.flush();
				DatagramPacket packet = new DatagramPacket(buffer,buffer.length,address,3331);
				// send packet 
				this.udp_socket.send(packet);
				this.server.read("notification sent to "+address.toString());
			}
			break ;
		default:
			
			break ;
		
		}
		
	}
	
	
	
	
	
}
