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
package cloud.jgo.utils.command;

import java.util.ArrayList;
import java.util.LinkedList;

import cloud.jgo.utils.command.execution.Execution;
/**
 * 
 * @author Martire91<br>
 * This class represents the remote command
 */
public class RemoteCommand extends LocalCommand{
	
	private static final long serialVersionUID = 1L;
	// i campi aggiuntivi rispetto a un localCommand
	private String senderHost = null ;
	
	/**
	 * This method returns the sender host
	 * @return the sender host
	 */
	public String getSenderHost() {
		return senderHost;
	}
	
	/**
	 * This method sets the sender host
	 * @param senderHost the sender host
	 */
	public void setSenderHost(String senderHost) {
		this.senderHost = senderHost;
	}
	
	/**
	 * This method returns the recipient host
	 * @return the recipient host
	 */
	public String getRecipientHost() {
		return recipientHost;
	}
	
	/**
	 * This method sets the recipient host
	 * @param recipientHost the recipient host
	 */
	public void setRecipientHost(String recipientHost) {
		this.recipientHost = recipientHost;
	}
	
	private String recipientHost = null ;

	public RemoteCommand(String command, String help, Execution execution) {
		super(command, help, execution);
		/*
		 
		JGO Auto-generated constructor stub
		Author : £ wasp91 £
		Date 03 gen 2018
		
		*/
	}

	public RemoteCommand(String command, String help) {
		super(command, help);
		/*
		 
		JGO Auto-generated constructor stub
		Author : £ wasp91 £
		Date 03 gen 2018
		
		*/
	}
	
	/**
	 * This method splits the command for parameters
	 * @param inputCommand the input command
	 * @param command the command
	 * @return the parameters
	 */
	public static Parameter[]split(String inputCommand,RemoteCommand command){
		// 1 passo tolgo il comando 
				int indexFirstSpace = inputCommand.indexOf(" ");
			if(indexFirstSpace==-1){
				return null ;
			}
			else{
				inputCommand = inputCommand.substring(indexFirstSpace).trim();

				// 2 passo ottengo l'array di params con gli eventuali valuesinput

				String[]split = inputCommand.split("-");
				java.util.List<String>fullSplit = new LinkedList<>();
				
				for (int i = 0; i < split.length; i++) {
					if(!split[i].isEmpty()){
						fullSplit.add(split[i]);
					}
				}
				String param = null ;
				String inputValue = null ;
				split = new String[fullSplit.size()];
				java.util.List<Parameter>paramsList = new ArrayList<>();
				for (int i = 0; i < split.length; i++) {
					// rinserisco nell'array
					split[i] =fullSplit.get(i).trim();
					
					String[]splitForSpace = split[i].split(" ");
					
					int size = splitForSpace.length ;
					
					switch(size){
					
					case 1 :
						
						/**
						 *  UNICO PARAM
						 */
						
						param = splitForSpace[0];
						
						// verifico che lo sia effettivamente 
						
						if(command.isParameter(param)){
							
							Parameter p = command.param(param);
							
							// aggiungo alla list
							
							paramsList.add(p);
						}
						
						break ;
						
						
					case 2 :


						/**
						 *  PARAM/VALUE   - con valore non spaziato
						 */
						
						// sono semplicemente param value e il valore è senza spazi per esempio : name Marco e non name Marco Piero, chiaro ?
						param = splitForSpace[0];
						
						inputValue = splitForSpace[1];
						
						// verifico che sia un param del comando
						
						if (command.isParameter(param)) {
							
							Parameter p = command.param(param);
							
							if(p.hasInputValueExploitable()){
								// imposto il suo valore da input
								
								p.setInputValue(inputValue);
								
								// aggiungo alla lista 
								
								paramsList.add(p);
								
								// facciamo un test finora
							}
						}
						
						break ;
						
						default:
							
							/**
							 *  PARAM/VALUES + OF 2
							 */
							
							// qui dobbiamo lavorare sulla stringa prima ottenuta
							
							
							// in tanto prendo il nome del param 
							
							int indexFirstSpace_2 = split[i].indexOf(" ");
							
							param = split[i].substring(0, indexFirstSpace_2);
							
							
							// ora prendo tutto ciò che fa parte del valore da input
							
							inputValue = split[i].substring(indexFirstSpace_2).trim();
							
							// verifico che sia un parametro del comando effettivamente
							
							if (command.isParameter(param)) {
								
								Parameter p = command.param(param);
								
								// verifico che sia richiesto un value from input
								
								if (p.hasInputValueExploitable()) {
									p.setInputValue(inputValue);
									
									// aggiungo alla lista 
									paramsList.add(p);
								}
								
							}
							
					}
					
				}
				Parameter[]parameters = new Parameter[paramsList.size()];
				for (int j = 0; j <paramsList.size(); j++) {
					parameters[j] = paramsList.get(j);
				}
				return parameters;	
			}
	}
}
