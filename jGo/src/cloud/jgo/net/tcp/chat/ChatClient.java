package cloud.jgo.net.tcp.chat;

import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import cloud.jgo.net.Connection;
import cloud.jgo.net.tcp.NoReadingSourceException;
import cloud.jgo.net.tcp.TCPClient;
// poi devo segnalare che di questa classe basta specificare l'oggetto
public class ChatClient extends TCPClient{
	
	private boolean online = false ;
	
	
	public void setOnline(boolean online) {
		this.online = online;
	}
	public ChatClient(String host,int port,String username) throws ClassNotFoundException, IOException {
		// TODO Auto-generated constructor stub
		setName(username);
		this.online = connect(host, port);
		// se la chat è online ricevo lo stato attuale della chat
		if(this.online){
			
			String currentStateChat = (String) getConnection().receive();
			
			// lo stampo
			try {
				read(currentStateChat);
			} catch (NoReadingSourceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	/**
	 * Costruttore vuoto,però attenzione non connette al server
	 * e inoltre non setta l'username del client
	 */
	public ChatClient() {
		
	}
	public boolean isOnline() {
		return online;
	}
	@Override
	public void communicates(Connection connection) throws IOException, ClassNotFoundException {
		while(true){
			String command = write();
			
			Mexg mex = new Mexg(this.getName(),command);
			
			connection.send(mex);
				
			// e dopo aver mandato il mex close al server che capisce che anche essoù
			// che deve chudere la connessione con il client,altrimenti il client si scollega
			// ma nel server rimane attivo l'handler che gestiva quel client
			
             if(command.equals("close")){
				
				// qui setto a offline lo stato della chat client
				this.online = false ;
				// esco dal ciclo per velocizzarmi
				break ;
			}
		}
		
		// chiudo la socket e i suoi stream
		connection.closeConnection();
	}
	
	
}

