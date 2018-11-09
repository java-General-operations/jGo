package cloud.jgo.net.tcp.chat;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.SocketException;

import javax.swing.JOptionPane;

import cloud.jgo.net.tcp.NoReadingSourceException;

public class NotifierClientSide extends Thread{

	
	/*
	 * N.B.
	 * 
	 * Nota bene ,questo programma va
	 * eseguito su un computer singolo,nel senzo che
	 * ebbene che ogni computer client ,abbia un solo
	 * NotifierClientSide,in quanto questi oggetti si mettono
	 * in ascolto tutti sulla stessa porta
	 */
	
	
	
	// questo non è  altro che un server sul client
	// nella maggior parte dei casi di genere udp
	// che non fa altro che stare li in attesa delle pacchetti
	// che il server gi invia,questo programma va fatto partire insieme al client
	// in tal modo,i due essendo thread partono indipendentemente
	// la porta di default del notifier client è la 3331 sia in tcp che in udp
	private int typeNotifier ;
	private ServerSocket tcp_server = null ;
	private java.net.DatagramSocket udp_server = null ;
	private ChatClient client = null;
	public static final int TYPE_TCP =0;
	public static final int TYPE_UDP =1 ;
	
	@Override
	public void run() {
		try {
			receiveNotifications();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void setType(int typeNotifier){
		this.typeNotifier = typeNotifier ;
	}
	
	
	public NotifierClientSide(ChatClient client) {
		// me serve per vedere quando l'utente chiude la chat
		this.client  = client ;
	}
	
	
	public void receiveNotifications() throws IOException{
		
		switch(this.typeNotifier){
		
		case NotifierServerSide.TYPE_UDP :
			
			// attivo il server udo e accetto i pacchetti
			
			udp_server = new DatagramSocket(3331);
			
			
			while(this.client.isOnline()){
				byte[]buffer = new byte[1024] ;
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				// ricevo il pacchetto 
				this.udp_server.receive(packet);
				// prendo i byte del pacchetto
				buffer = packet.getData() ;
				ByteArrayInputStream fis = new ByteArrayInputStream(buffer);
				DataInputStream in = new DataInputStream(fis);
				String chatState = in.readUTF();
				
				/*
				 * Stampo per il momento lo stato della chat in una println
				 */
				try {
					this.client.read(chatState);
				} catch (NoReadingSourceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fis.close();
				in.close();
			}
			
			// qui si chiude il programma client ,quindi siamo sicuri usciti dalla chat
			
		
			break ;
			
		case NotifierServerSide.TYPE_TCP:
			
			
			break ;
			
			default :
				
			  break ;	
		
		}
		
	}
	
	
	
	
}
