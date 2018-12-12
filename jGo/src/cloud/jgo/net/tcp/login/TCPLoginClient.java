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

import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;

import cloud.jgo.net.Connection;
import cloud.jgo.net.tcp.NoReadingSourceException;
import cloud.jgo.net.tcp.TCPClient;

/**
 * 
 * @author Martire91<br>
 *         This class is a login TCP client
 *
 */
public abstract class TCPLoginClient extends TCPClient implements Authenticatable {

	// queste due variabili assumono il valore da remoto
	private boolean logged = false;
	private int attempts = 0;
	private int copiedValueAttempts;
	private int countLevel = 1;
	private boolean finishedAttempts = false;

	@Override
	public boolean isLogged() {
		return this.logged;
	}

	/**
	 * Questo metodo va chiamato dall'utente però nel suo codice al posto giusto
	 */
	@Override
	public void logout() {
		if (isLogged()) {
			logged = false;
			countLevel = 1; // quindi si ritorna al primo livello
			// ripristino i tentativi a valore default
			attempts = this.copiedValueAttempts;
			finishedAttempts = false;
		} else {
			// quà si è già disconnessi
		}
	}

	@Override
	public void communicates(Connection connection) throws IOException, ClassNotFoundException {
		while (true) {
			switch (countLevel) {

			case TCPLoginHandlerConnection.LEVEL_1:

				/*
				 * USERNAME
				 */

				try {
					read((String) connection.receive());
				} catch (NoReadingSourceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// rispondo

				String username = write();

				// invio l'user

				connection.send(username);

				countLevel++;

				break;

			case TCPLoginHandlerConnection.LEVEL_2:

				/*
				 * PASSWORD
				 */

				try {
					read((String) connection.receive());
				} catch (NoReadingSourceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// rispondo

				String passw = write();

				// invio l'user

				connection.send(passw);

				countLevel++;

				break;

			case TCPLoginHandlerConnection.LEVEL_3:

				// ricevo il pacchetto

				SimpleEntry<Boolean, Integer> packetReceived = (SimpleEntry<Boolean, Integer>) connection.receive();

				this.attempts = packetReceived.getValue().intValue();

				this.copiedValueAttempts = this.attempts;
				if (this.attempts == 0) {
					// comunico che sono finiti
					// in tentativi
					finishedAttempts = true;
				}

				this.logged = packetReceived.getKey().booleanValue();

				if (logged) {

					// access granted
					/// okok finalmente ci siamo autenticati
					try {
						read("Access Granted @");
					} catch (NoReadingSourceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					countLevel++;

				} else {

					// access failed
					try {
						read("Access Failed #");
					} catch (NoReadingSourceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						read("Remaining attempts :" + this.attempts);
					} catch (NoReadingSourceException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// qui ritorno al punto di partenza

					countLevel = TCPLoginHandlerConnection.LEVEL_1;
				}

				break;

			case TCPLoginHandlerConnection.LEVEL_4:

				doAccessGranted();

				break;
			}

			// qui e quindi dopo il blocco swicth
			// controllo se i tentativi sono finiti
			if (finishedAttempts) {
				break; // e qui esco dal ciclo
			}

		}
		if (finishedAttempts) {
			try {
				read("We are sorry but the attempts are over #");
			} catch (NoReadingSourceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// qui eseguiamo il nostro metodo
			// nel caso di accesso negativo

			doAccessFailed();
		}

	}

	@Override
	public int getAttempts() {
		return this.attempts;
	}

}
