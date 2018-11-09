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
package cloud.jgo;

import javax.swing.JFrame;

import cloud.jgo.£.Effect;

public class MoveFrame extends Thread {

	
	private JFrame frame ;
	private Effect verify;
	private int originalX,originalY = 0;
	public MoveFrame(JFrame frame,int x,int y,Effect mode) {
		// TODO Auto-generated constructor stub
	this.frame = frame;
	verify = mode;
	this.originalX = x;
	this.originalY = y;
	
	}
	
	@Override
	public void run() {
		try {
			move();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 case REALLY_SLOW :
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break ;
			
		case SLOW:
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break ;
			
			case FAST :
			
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break ;
			
			case REALLY_FAST :
				
				try {
					Thread.sleep(3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break ;
				
			case SUPER_FAST :
				
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break ;
			
			case IMPERCEPTIBLE :
				// nothing for moment
				break ;
			
			default :
				
				break ; 
	 */
	
	private void move() throws InterruptedException{
		int countX =0;
		int countY =0;
		if (verify==Effect.SLOW) {
			while (true) {
				Thread.sleep(10);
				countX = this.frame.getLocation().x;
				countY = this.frame.getLocation().y;
				
				if(countX<originalX){
					countX++ ;
				}
				else{
					countX-- ;
				}
				if(countY<originalY){
					countY++ ;
				}
				else{
					countY-- ;
				}
				
				// qui facciamo il controllo
				if(countX == originalX && countY == originalY){
					break ;
				}
				this.frame.setLocation(countX,countY);
				
			}
			// e qui ti fermi 
		}
		else if(verify == Effect.FAST){
			while (true) {
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				countX = this.frame.getLocation().x;
				countY = this.frame.getLocation().y;
				
				if(countX<originalX){
					countX++ ;
				}
				else{
					countX-- ;
				}
				if(countY<originalY){
					countY++ ;
				}
				else{
					countY-- ;
				}
				// qui facciamo il controllo
				if(countX == originalX && countY == originalY){
					break ;
				}
				this.frame.setLocation(countX,countY);
				
			}
			// e qui ti fermi 
		}
		else if (verify == £.Effect.REALLY_SLOW) {
			while (true) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				countX = this.frame.getLocation().x;
				countY = this.frame.getLocation().y;
				
				if(countX<originalX){
					countX++ ;
				}
				else{
					countX-- ;
				}
				if(countY<originalY){
					countY++ ;
				}
				else{
					countY-- ;
				}
				
				// qui facciamo il controllo
				if(countX == originalX && countY == originalY){
					break ;
				}
				this.frame.setLocation(countX,countY);
				
			}
			// e qui ti fermi 
		}
		else if(verify == Effect.REALLY_FAST){
			while (true) {
				try {
					Thread.sleep(3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				countX = this.frame.getLocation().x;
				countY = this.frame.getLocation().y;
				
				if(countX<originalX){
					countX++ ;
				}
				else{
					countX-- ;
				}
				if(countY<originalY){
					countY++ ;
				}
				else{
					countY-- ;
				}
				
				// qui facciamo il controllo
				if(countX == originalX && countY == originalY){
					break ;
				}
				this.frame.setLocation(countX,countY);
				
			}
			// e qui ti fermi 
		}
		else if (verify == £.Effect.SUPER_FAST) {
			while (true) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				countX = this.frame.getLocation().x;
				countY = this.frame.getLocation().y;
				
				if(countX<originalX){
					countX++ ;
				}
				else{
					countX-- ;
				}
				if(countY<originalY){
					countY++ ;
				}
				else{
					countY-- ;
				}
				
				// qui facciamo il controllo
				if(countX == originalX && countY == originalY){
					break ;
				}
				this.frame.setLocation(countX,countY);
				
			}
		}
		else if(verify==Effect.IMPERCEPTIBLE){
			while (true) {
				countX = this.frame.getLocation().x;
				countY = this.frame.getLocation().y;
				
				if(countX<originalX){
					countX++ ;
				}
				else{
					countX-- ;
				}
				if(countY<originalY){
					countY++ ;
				}
				else{
					countY-- ;
				}
				
				// qui facciamo il controllo
				if(countX == originalX && countY == originalY){
					break ;
				}
				this.frame.setLocation(countX,countY);
				
			}
			
		}
		else{
			try {
				throw new NotSupportedEffectException();
			} catch (NotSupportedEffectException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}

	
	
	
	
	
	
	
}
