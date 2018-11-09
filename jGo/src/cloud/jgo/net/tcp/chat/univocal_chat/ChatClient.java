package cloud.jgo.net.tcp.chat.univocal_chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import javax.swing.JOptionPane;

import cloud.jgo.net.Connection;
import cloud.jgo.net.tcp.NoReadingSourceException;
import cloud.jgo.net.tcp.TCPClient;
import cloud.jgo.net.tcp.TCPConnection;
import cloud.jgo.net.tcp.chat.Mexg;

public class ChatClient extends TCPClient{
	
	
	@Override
	public void run() {
		
		if(reicevedModel!=null && sentModel!=null){
			// parte l'iterazione con modello
			communicatesWithModel();
		}
		else{
			try {
				communicates(this.getConnection());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	private String username ;
	private ManageReiceved reiceved = null ;
	private ManageSent sent = null ;
	private ManageReiceved reicevedModel = null ;
	private ManageSent sentModel = null ;
	
	public String getUsername() {
		return username;
	}

	public void setReicevedModel(ManageReiceved reicevedModel) {
		this.reicevedModel = reicevedModel;
	}

	public void setSentModel(ManageSent sentModel) {
		this.sentModel = sentModel;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	/**
	 * Ridefinisco il metodo connect
	 * aggiungendo la questione che se la connessione va a buon file
	 * allora oltre a inizializzare gli stream con il super.connect()
	 * si settano anche gli stream dei gestori sent e reiceved
	 */
	@Override
	public boolean connect(String host, int port) throws IOException {
		// TODO Auto-generated method stub
		boolean connected = super.connect(host, port);
		if(connected){
			ManageReiceved.setInput(((TCPConnection)this.getConnection()).getIn());
			ManageSent.setOutput(((TCPConnection)this.getConnection()).getOut());
		}
		return connected ;
	}
	
	
	public void communicatesWithModel(){
		
		
		// attivo semplicemente i modelli
		
		this.reicevedModel.start();
		this.sentModel.start();
	
	}


	
	@Override
	public void communicates(Connection connection) throws IOException, ClassNotFoundException {
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
							read("The server is abandoning the chat...");
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
					// se la chat client non è chiusa e la server chat non è chiusa
					// manda il mex ,altrimenti non mandare il mex perchè non ha senzo
					
						
							try {
								ManageSent.getOutput().writeObject(new Mexg(getUsername(), mex));
							    ManageSent.getOutput().flush();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
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
								// test
								ManageReiceved.running = false ;
								ManageSent.running = false ;
								break ;
							}
				}
			}
		});	sent.start();	
		
		
	}

}
