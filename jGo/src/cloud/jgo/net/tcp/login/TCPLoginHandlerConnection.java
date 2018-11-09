/**
 * JGO - A pure Java library,
 * its purpose is to make life easier for the programmer.
 *
 * J - Java
 * G - General
 * O - Operations
 *
 * URL Software : https://www.jgo.cloud/
 * URL Documentation : https://www.jgo.cloud/docs/
 *
 * Copyright © 2018 - Marco Martire (www.jgo.cloud)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License.
 *
 * You may obtain a copy of License at :
 * https://www.jgo.cloud/LICENSE.txt
 *
 * To collaborate on this project, you need to do it from the software site.
 * 
 */
package cloud.jgo.net.tcp.login;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;

import cloud.jgo.net.tcp.NoReadingSourceException;
import cloud.jgo.net.tcp.TCPHandlerConnection;
/**
 * 
 * @author Martire91<br>
 * This class is the Login TCP handler
 *
 */
public abstract class TCPLoginHandlerConnection extends TCPHandlerConnection implements Authenticatable{
	
	public static final int LEVEL_1 = 1 ; // username
	public static final int LEVEL_2 = 2 ; // password
	public static final int LEVEL_3 = 3 ; // controlla dati
	public static final int LEVEL_4 = 4 ; // accesso granted 
	private int attempts ;
	private int countLevel = 1 ; // si parte dal chiedere l'username
	private final static String finishedAttemptsm = "finished_attempts";
	private boolean logged = false ;
	
	@Override
	public void logout() {
	    if (isLogged()) {
	    	countLevel = 1 ; // quindi si ritorna al primo livello
		    attempts = ((TCPLoginServer)getServer()).getCopiedValueAttempts();
		    //System.out.println("Ricaricamento dei tentativi - tentativi :"+attempts);
		    this.logged = false ;
		}
	}
	
	@Override
	public void manage()
			throws ClassNotFoundException, IOException, InterruptedException, HeadlessException, AWTException {
		attempts = ((TCPLoginServer)getServer()).getAttempts();
		String username = null ;
		String password = null ;
		while(!isHandlerClosed()){
		    if(attempts==0){
		    	// esco dal ciclo
		    	break ;
		    }
			switch(countLevel){
			
			case LEVEL_1 :
				
				// chiedo la username 
				
				
				send("Username :");
				
				try {
					getServer().read("Waiting for authentication - Username ...");
				} catch (NoReadingSourceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				username = (String) receive();
				
				System.out.println("Username received @");
				
				// si va avanti
				
				countLevel++ ;
				
				break ;
				
			case LEVEL_2 :
				
			    send("Password :");
				
			    try {
					getServer().read("Waiting for authentication - Password ...");
				} catch (NoReadingSourceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				password = (String) receive();
				
				System.out.println("Password received @");
				
				countLevel++ ;
				
				break ;
				
			case LEVEL_3:
				
				// controllo dei dati
				
				// eseguo il login
				
				this.logged = ((TCPLoginServer)server).login(username, password);
				
				if (this.logged) {

					// qui ci spostiamo all'ultimo livello
					countLevel++ ;
				}
				else{
					attempts-- ; // 1 tentativo in meno
					countLevel = LEVEL_1 ;
				}
				// in entrambi i casi invio il booleano logged
				// qui invio un oggetto doppio
				
				SimpleEntry<Boolean,Integer> packet = 
				new SimpleEntry<Boolean, Integer>(new Boolean((this.logged)),new Integer(attempts));
				
				// invio il pacchetto
				
				send(packet);
				
				break ;
				
				
			case LEVEL_4 :
				
				doAccessGranted();
				
				break ;
			
			}
				
		}
		
		// poi qui aggiornare ...
		if(attempts==0){
			
			
			// qui eseguo il metodo doAccessFailed()
			
			doAccessFailed();
			
		}
		
		
	}

	@Override
	public boolean isLogged() {
		// TODO Auto-generated method stub
		return this.logged ;
	}

	@Override
	public int getAttempts() {
		// TODO Auto-generated method stub
		return this.attempts ;
	}

}
