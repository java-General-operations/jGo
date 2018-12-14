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
import java.util.ArrayList;

import cloud.jgo.£;

/**
 * 
 * @author Martire91<br>
 *         <strong>Description</strong><br>
 *         <p>
 * 		This class simulates the windows CMD.
 *         </p>
 *         <p>
 * 		It is useful, however, for simple commands.
 *         </p>
 *         COMPLETATO @
 */
public final class WinTerminal extends Terminal {

	/**
	 * This method shows all running processes
	 * 
	 * @return the processes array
	 * @throws IOException
	 *             1 exception
	 */
	public String[] displayAllProcess() throws IOException {
		Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");
		InputStream input = p.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		String leggi;
		ArrayList<String> list = new ArrayList<>();
		while ((leggi = reader.readLine()) != null) {
			list.add(leggi);
		}
		reader.close();
		String[] process = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			process[i] = list.get(i);
		}
		return process;
	}

	/**
	 * This method close a process.
	 * 
	 * @param nameProcess
	 *            The process name you want to close
	 * @return the output command
	 * @throws IOException
	 *             1 exception
	 * @throws InterruptedException
	 *             2 exception
	 */
	public String closeProcess(String nameProcess) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		Process p = runtime.exec("C:\\windows\\system32\\cmd.exe");
		InputStream in = p.getInputStream();
		OutputStream out = p.getOutputStream();
		String command = "TASKKILL /IM " + nameProcess + "\n\r";
		out.write(command.getBytes());
		out.flush();
		out.close();
		p.waitFor();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String leggi;
		StringBuffer buffer = new StringBuffer();
		while ((leggi = reader.readLine()) != null) {
			buffer.append(leggi);
		}
		reader.close();
		return buffer.toString();
	}

	@Override
	public String command(String command) throws IOException, InterruptedException {
		/*
		 * 
		 * JGO Auto-generated method stub Author : £ wasp91 £ Date 17 nov 2017
		 * 
		 */
		Process p = Runtime.getRuntime().exec("C:\\Windows\\System32\\cmd.exe");
		OutputStream out = p.getOutputStream();
		InputStream in = p.getInputStream();

		command = command + "\r\n";

		byte[] buffer = command.getBytes();

		// scrivo i byte sullo stream di output

		out.write(buffer);

		out.flush();

		out.close();

		p.waitFor();

		/*
		 * Okok qui leggo l'output del comando
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String leggi;
		StringBuffer bufferS = new StringBuffer();
		while ((leggi = reader.readLine()) != null) {
			System.out.println(leggi);
			bufferS.append(leggi + "\n");
		}

		// okok lasciamo gli streams aperti per altri eventuali comandi
		// vediamo come va in tanto
		reader.close();
		return bufferS.toString();
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
	protected void implOpen() {
		// TODO Auto-generated method stub
		String cmd = £._I();
		if (cmd.equals(getExitCommand())) {
			close();
		} else {
			cmd = cmd + "\r\n";
			byte[] buffer = cmd.getBytes();
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
	public String getExitCommand() {
		// TODO Auto-generated method stub
		return (String) this.exitCommand;
	}

	@Override
	public void setExitCommand(String command) {
		// TODO Auto-generated method stub
		this.exitCommand = command;
	}

	@Override
	public String getCommandRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "cmd";
	}
}
