package cloud.jgo.net.tcp.chat.univocal_chat;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JOptionPane;

import cloud.jgo.net.tcp.DefaultSocket;
import cloud.jgo.net.tcp.DefaultTCPServer;
import cloud.jgo.net.tcp.NoReadingSourceException;
import cloud.jgo.net.tcp.TCPServerConfiguration;
import cloud.jgo.net.tcp.TCPServerTypes;
import cloud.jgo.net.tcp.chat.Mexg;
/**
 * 
 * @author FRXWasp91
 * @version 10.01
 * 
 * <p>Questa classe rappresenta una chat ,lato server.</p>
 * <p>Fa parte del pacchetto univocal_chat</p>
 *
 */
public class ChatServer extends DefaultTCPServer{

	static {
		TYPE_SERVER = TCPServerTypes.TYPE_UNIVOCAL_CHAT_TCP ;
	}
	
	/*
	 * Adesso ci dobbiamo occupare di quando o il server o il client decide
	 * di abbandonare la chat
	 */
	
	@Override
	public void run() {
		
		// ridefinisco il metodo run a seconda delle nuove esigenze
		// e cioè ridefinendolo non do la possibilità di usare questo server
		// a multi connessione,ma solo a connessioni singole
		
		if(reicevedModel!=null && sentModel!=null){
			try {
				try {
					acceptRequestWithModel();
				} catch (NoReadingSourceException e) {
					/*
					 
					JGO Auto-generated catch block
					Author : £ wasp91 £
					Date 22 nov 2017
					
					*/
					e.printStackTrace();
				}
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			try {
				try {
					acceptRequest();
				} catch (NoReadingSourceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	 
	
	private ManageReiceved reiceved = null ;
	private ManageSent sent = null ;
	


	public void setReicevedModel(ManageReiceved reicevedModel) {
		this.reicevedModel = reicevedModel;
	}
	
	public void setSentModel(ManageSent sentModel){
		
		this.sentModel = sentModel;
		
		
	}
	
	
	
	public ChatServer() {
		// LO SCRIVO NELLA CONFIGURAZIONE
	}


	private ManageReiceved reicevedModel = null ;
	private ManageSent sentModel = null ;
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	private String username ;
	
	
	
	/**
	 * Questo metodo da per scontato che sia sia inizializzato sia il reiceve che il sent model
	 * e non fa altro che attivare i modelli
	 * @throws NoReadingSourceException 1 exception
	 */
	@Override
	public void acceptRequestWithModel()
			throws IOException, CloneNotSupportedException, HeadlessException, ClassNotFoundException, AWTException, NoReadingSourceException {
		Socket socket = this.acceptRequest();
		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		
		// chiudo il server 
		
		this.closeServer();
		
		ManageSent.setOutput(out);
		ManageReiceved.setInput(in);
		
		this.sentModel.start();
		this.reicevedModel.start();
		
	}
	
	
	
	@Override
	public DefaultSocket acceptRequest() throws IOException, NoReadingSourceException {
		// TODO Auto-generated method stub
		DefaultSocket socket = super.acceptRequest();
		System.out.println("Connessione ricevuta");
		// inio gli stream 
		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		
		// chiudo il server 
		
		this.closeServer();
		
		// setto gli stream dei gestori
		
		ManageReiceved.setInput(in);
		ManageSent.setOutput(out);
		
		reiceved = new ManageReiceved(new Runnable() {
			
			@Override
			public void run() {
				
			while (ManageReiceved.running) { 
				Mexg mex=null;
						try {
							mex = (Mexg) ManageReiceved.getInput().readObject();
							if(mex.getMex().equals("close")){
								 ManageReiceved.running = false ;
								 ManageSent.running = false ;
								 try {
									read("The client is abandoning the chat...");
								} catch (NoReadingSourceException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								 try {
									read("Is waiting for your greeting");
								} catch (NoReadingSourceException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								 break ;
							 }
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					try {
						try {
							read(mex.toString());
						} catch (NoReadingSourceException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
				
			}
		});reiceved.start();
		
		sent = new ManageSent(new Runnable() {
			
			@Override
			public void run() {
				
				while (ManageSent.running) { 
					
					String mex = null;
					try {
						mex = write();
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					Mexg mexg = new Mexg(getUsername(),mex);
					
					try {
						ManageSent.getOutput().writeObject(mexg);
						ManageSent.getOutput().flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					// dopo averlo inviato faccio il controllo
					if(mex.equals("close")){
						
						try {
							try {
								read("waiting for the greeting ...");
							} catch (NoReadingSourceException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						ManageReiceved.running = false ;
						ManageSent.running = false ;
						break ;
					}
					
					
				}
				
				
				
			}
		});sent.start();
		
		return socket ;
	}

}
