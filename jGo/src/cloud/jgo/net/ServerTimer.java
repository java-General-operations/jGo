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
package cloud.jgo.net;
import java.util.Timer;
import java.util.TimerTask;

import cloud.jgo.£;
/**
 * 
 * @author Martire91<br>
 * This class represents the server timer
 */
public class ServerTimer {
	private int sec = 0 ;
	private Timer timer = null;
	private TimerTask task = null ;
	private Server server = null ;
	private boolean expired = false ;
	int timer_value = 0 ;
	
	public ServerTimer(Server server,int sec) {
		/*
		 
		JGO Auto-generated constructor stub
		Author : £ wasp91 £
		Date 22 nov 2017
		
		*/
		this.sec = sec ;
		this.server = server ;
		this.timer = new Timer();
	}
	
	public boolean isExpired() {
		return expired;
	}
	
	/**
	 * This method starts the timer
	 */
	public void startTimer(){
		
	this.task = new TimerTask() {
		
		@Override
		public void run() {
		
			timer_value++ ;
			£.beep();
			if(timer_value==sec){
				timer.cancel();
				
				expired = true ;
				
				// comunico che il timer è finiro per cui
				// chiudo il server 
				
				if(!server.isClosed()){
					
					server.closeServer();
				}
			}
				
		}
	};timer.schedule(this.task, 0, 1000);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
