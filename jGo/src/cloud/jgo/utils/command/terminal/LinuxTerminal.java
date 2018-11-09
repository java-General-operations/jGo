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
package cloud.jgo.utils.command.terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import cloud.jgo.£;

/**
 * 
 * @author Martire91<br>
 * <p>This class simulates the linux Shell.</p>
 * <p>It is useful, however, for simple commands.</p>
 * 
 *
 */
public final class LinuxTerminal extends Terminal{
	
	public static final String pathExecutable = "/bin/xterm";
	
	/**
	 * This method executes the commands
	 * @param commands the commands
	 * @throws InterruptedException 1 exception
	 */
	public void commands(String...commands) throws InterruptedException {
		Process p = null ;
		try {
			p = Runtime.getRuntime().exec("/bin/bash");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		OutputStream out = p.getOutputStream();
		InputStream in = p.getInputStream();
	
		for (int i = 0; i < commands.length; i++) {
			String command = commands[i];
			command = command+"\n";
			
			byte[]buffer = command.getBytes();
			
			// scrivo i byte sullo stream di output
			
			try {
				out.write(buffer);
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			p.waitFor();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String leggi ;
		StringBuffer bufferS = new StringBuffer();
		try {
			while((leggi = reader.readLine())!=null){
				System.out.println(leggi);
				bufferS.append(leggi+"\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String command(String command) throws IOException, InterruptedException {
		Process p=null;
		try {
			p = Runtime.getRuntime().exec("/bin/bash");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OutputStream out = p.getOutputStream();
		InputStream in = p.getInputStream();
	
		command = command+"\n";
		
		byte[]buffer = command.getBytes();
		
		// scrivo i byte sullo stream di output
		
		try {
			out.write(buffer);
			out.flush();
			out.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		p.waitFor();
		
		/*
		 * Okok qui leggo l'output del comando
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String leggi ;
		StringBuffer bufferS = new StringBuffer();
		try {
			while((leggi = reader.readLine())!=null){
				System.out.println(leggi);
				bufferS.append(leggi+"\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// okok lasciamo gli streams aperti per altri eventuali comandi
		// vediamo come va in tanto
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bufferS.toString();
	}
	@Override
	public void implOpen() {
		String cmd = £._I();
		if (cmd.equals(getExitCommand())) {
			close();
		}
		else{
			cmd = cmd+"\n";
			byte[]buffer = cmd.getBytes();
			try {
				this.process.getOutputStream().write(buffer);
				this.process.getOutputStream().flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
    @Override
    public void close() {
    	// TODO Auto-generated method stub
    	super.close();
    	try {
			this.process.getOutputStream().flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			this.process.getOutputStream().close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			this.process.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


	@Override
	public String getExitCommand() {
		// TODO Auto-generated method stub
		return (String) this.exitCommand;
	}


	@Override
	public void setExitCommand(String command) {
		// TODO Auto-generated method stub
		this.exitCommand = command ;
	}
	@Override
	public String getCommandRequest() {
		// TODO Auto-generated method stub
		return "$_";
	}
}
