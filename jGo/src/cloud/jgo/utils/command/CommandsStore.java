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

import java.awt.HeadlessException;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import cloud.jgo.£;
import cloud.jgo.io.File;
import cloud.jgo.utils.command.execution.Execution;

/**
 * 
 * @author Martire91 This class Represents a store of predefined commands that
 *         can be executed and ready to use. And from time to time will be
 *         updated with new useful commands..
 * 
 */
public class CommandsStore implements Serializable {
	public static final long serialVersionUID = 1L;
	public static final LocalCommand OPEN_COMMAND = new RemoteCommand("open", "This command opens somethings");
	public static final LocalCommand FILE_COMMAND = new RemoteCommand("file", "This command works with the files");
	public static final LocalCommand ALERT_COMMAND = new RemoteCommand("alert", "This command opens the custom alert"); // ha
																														// un
																														// oggetto
																														// condiviso

	private static String commandPositive = "Command|Parameter # ( OK ) @";
	private static String commandNegative = "Unrecognized command #";
	private static String commandNegativeAbsenceObject = "No Object set #";

	// params - open
	private static Parameter terminalParam, notepadParam, regeditParam, paramServices, fileParam;

	// params - file

	private static Parameter createParam, identifiesParam, renameParam, deleteParam, copyParam, moveParam, writeParam,
			readParam, openParam, getParam;

	// params - alert

	private static Parameter textParam, titleParam, iconParam, typeParam, showParam;
	private static JGOptionPane joptionPane = new CommandsStore.JGOptionPane();

	static {
		/**
		 * @author Martire91 init params command - open
		 */

		// creo i params
		terminalParam = OPEN_COMMAND.addParam("terminal", "This param open the windows cmd");
		notepadParam = OPEN_COMMAND.addParam("notepad", "This param open the windows notepad");
		regeditParam = OPEN_COMMAND.addParam("regedit", "This param open the windows regedit");
		paramServices = OPEN_COMMAND.addParam("services", "This param open the windows services");

		// imposto i comandi con valore da input
		terminalParam.setInputValueExploitable(true);
		notepadParam.setInputValueExploitable(true);
		regeditParam.setInputValueExploitable(true);
		paramServices.setInputValueExploitable(true);

		// imposto le esecuzioni
		terminalParam.setExecution(new Execution() {

			@Override
			public Object exec() {
				int valueInput = 0;
				try {
					valueInput = Integer.parseInt(terminalParam.getInputValue());
					£.openTerminal(valueInput);
					return commandPositive.replace("#", terminalParam.getOnlyParam());

				} catch (java.lang.NumberFormatException e) {
					// TODO: handle exception
					return "The value from input is invalid #";
				}
			}
		});

		notepadParam.setExecution(new Execution() {

			@Override
			public Object exec() {

				int valueInput = 0;
				try {
					valueInput = Integer.parseInt(terminalParam.getInputValue());
					£.openNotepad(valueInput);
					return commandPositive.replace("#", terminalParam.getOnlyParam());

				} catch (java.lang.NumberFormatException e) {
					// TODO: handle exception
					return "The value from input is invalid #";
				}
			}
		});

		regeditParam.setExecution(new Execution() {

			@Override
			public Object exec() {

				int valueInput = 0;
				try {
					valueInput = Integer.parseInt(terminalParam.getInputValue());
					£.openRegedit(valueInput);
					return commandPositive.replace("#", terminalParam.getOnlyParam());

				} catch (java.lang.NumberFormatException e) {
					// TODO: handle exception
					return "The value from input is invalid #";
				}
			}
		});

		paramServices.setExecution(new Execution() {

			@Override
			public Object exec() {
				int valueInput = 0;
				try {
					valueInput = Integer.parseInt(terminalParam.getInputValue());
					£.openServices(valueInput);
					return commandPositive.replace("#", terminalParam.getOnlyParam());

				} catch (java.lang.NumberFormatException e) {
					// TODO: handle exception
					return "The value from input is invalid #";
				}
			}
		});

		/**
		 * @author Martire91 init params command - file
		 *         createParam,renameParam,deleteParam,copyParam,moveParam,writeParam;
		 *         create : prende un valore da input cioè il path del file e crea un
		 *         oggetto condiviso che è il file stesso
		 * 
		 */

		createParam = FILE_COMMAND.addParam("create", "This parameter creates a file");
		identifiesParam = FILE_COMMAND.addParam("id", "This parameter identifies a file");
		renameParam = FILE_COMMAND.addParam("rename", "This parameter renames a file");
		deleteParam = FILE_COMMAND.addParam("delete", "This parameter removes a file");
		copyParam = FILE_COMMAND.addParam("copy", "This parameter copy a file");
		moveParam = FILE_COMMAND.addParam("move", "This parameter moves a file");
		writeParam = FILE_COMMAND.addParam("write", "This parameter writes a file");
		readParam = FILE_COMMAND.addParam("read", "This parameter reads a file");
		openParam = FILE_COMMAND.addParam("open", "This parameter opens a file");
		getParam = FILE_COMMAND.addParam("get", "This parameter returns the file"); // in realtà lo passa a un metodo
																					// che noi effettuandone l'override
																					// siamo in grado di lavorare sul
																					// file

		// create
		createParam.setInputValueExploitable(true);

		createParam.setExecution(new Execution() {

			@Override
			public Object exec() {

				String pathFileDestination = createParam.getInputValue();

				File file = new File(pathFileDestination);

				boolean created = false;
				try {
					created = file.createNewFile();

					if (created) {
						FILE_COMMAND.shareObject(file); // condivido l'oggetto
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// restituisco la risposta che in questo caso è una stringa che comunica l'esito
				// della creazione in questo caso
				return "File created :" + created + "\n" + commandPositive.replace("#", createParam.getOnlyParam());
			}
		});

		// identifies
		// questo parametro identifica un file e quindi lo condivide

		identifiesParam.setInputValueExploitable(true);

		identifiesParam.setExecution(new Execution() {

			@Override
			public Object exec() {
				String pathInputValue = identifiesParam.getInputValue();
				cloud.jgo.io.File file = new File(pathInputValue);

				if (file.exists()) {
					// I share a file
					FILE_COMMAND.shareObject(file);
					return "The file has been identified @" + "\n"
							+ commandPositive.replace("#", identifiesParam.getOnlyParam());
				} else {
					return "The file dosn't exist #";
				}
			}
		});

		// renameParam
		// qui se vi è un file condiviso allora si può rinominare altrimenti no
		renameParam.setInputValueExploitable(true);

		renameParam.setExecution(new Execution() {

			@Override
			public Object exec() {

				String newName = renameParam.getInputValue();

				// controllo se il parent di tutti questi params ha un oggetto condiviso

				if (FILE_COMMAND.getSharedObject() != null) {

					// rinomino il file

					£.moveFile(((File) FILE_COMMAND.getSharedObject()),
							new File(((File) FILE_COMMAND.getSharedObject()).getParentFile(), newName));

					return commandPositive.replace("#", renameParam.getOnlyParam());

				} else {
					return commandNegativeAbsenceObject;
				}

			}
		});

		// delete
		// senza valori da input

		deleteParam.setExecution(new Execution() {

			@Override
			public Object exec() {
				if (FILE_COMMAND.getSharedObject() != null) {

					// elimino il file

					boolean deleted = ((File) FILE_COMMAND.getSharedObject()).delete();

					if (deleted) {
						return commandPositive.replace("#", deleteParam.getOnlyParam());
					} else {
						return "The file will be deleted when the program is closed #";
					}
				} else {
					return commandNegativeAbsenceObject;
				}
			}
		});

		// write - interessante

		writeParam.setInputValueExploitable(true);
		writeParam.setExecution(new Execution() {

			@Override
			public Object exec() {

				if (FILE_COMMAND.getSharedObject() != null) {

					String textFile = writeParam.getInputValue();

					try {
						£.writeFile(((File) FILE_COMMAND.getSharedObject()), false, new String[] { textFile });
					} catch (HeadlessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return commandPositive.replace("#", writeParam.getOnlyParam());
				}

				else {
					return commandNegativeAbsenceObject;
				}
			}
		});

		readParam.setExecution(new Execution() {

			@Override
			public Object exec() {
				if (FILE_COMMAND.getSharedObject() != null) {

					File file = (File) FILE_COMMAND.getSharedObject();
					String textFile = null;
					try {
						textFile = £.readFile(file);
						if (textFile.endsWith("\n")) {
							int pos = textFile.lastIndexOf("\n");
							textFile = textFile.substring(0, pos);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					return textFile;
				} else {

					return commandNegativeAbsenceObject;

				}
			}
		});

		// questo comando prende un valore da input
		// che è il path Del File di destinazione
		copyParam.setInputValueExploitable(true);
		copyParam.setExecution(new Execution() {

			@Override
			public Object exec() {
				if (FILE_COMMAND.getSharedObject() != null) {

					String newPathName = copyParam.getInputValue();

					boolean copied = false;

					copied = £.copyFile(((File) FILE_COMMAND.getSharedObject()), new File(newPathName));

					return "Copied file :" + copied + "\n" + commandPositive.replace("#", copyParam.getOnlyParam());

				} else {
					return commandNegativeAbsenceObject;
				}
			}
		});

		moveParam.setInputValueExploitable(true);
		moveParam.setExecution(new Execution() {

			@Override
			public Object exec() {
				if (FILE_COMMAND.getSharedObject() != null) {

					File file = (File) FILE_COMMAND.getSharedObject();

					boolean moved = false;
					moved = £.moveFile(file, new File(moveParam.getInputValue()));
					return "Moved file :" + moved + "\n" + commandPositive.replace("#", moveParam.getOnlyParam());
				} else {
					return commandNegativeAbsenceObject;
				}
			}
		});

		getParam.setExecution(new Execution() {

			@Override
			public Object exec() {
				if (FILE_COMMAND.getSharedObject() != null) {

					// restituisco il file (in questo caso)
					// e lo restituisco al metodo open
					// che me lo manda nel metodo sul quale io utente final e ho il controllo
					// che è il metodo :
					return FILE_COMMAND.getSharedObject();
				} else {
					return commandNegativeAbsenceObject;
				}
			}
		});

		/**
		 * @author Martire91 parametri
		 * 
		 */

		ALERT_COMMAND.shareObject(joptionPane); // condivido qui
		textParam = ALERT_COMMAND.addParam("text", "this parameter sets the text");
		titleParam = ALERT_COMMAND.addParam("title", "this parameter sets the title");
		iconParam = ALERT_COMMAND.addParam("icon", "this parameter sets the icon");
		showParam = ALERT_COMMAND.addParam("show", "This parameter shows the alert window");
		typeParam = ALERT_COMMAND.addParam("type", "This parameter sets the message type");

		// text
		textParam.setInputValueExploitable(true);
		textParam.setExecution(new Execution() {

			@Override
			public Object exec() {

				// prendo il valore da input che in questo caso sarà
				// il testo dell'alert
				String text = textParam.getInputValue();

				joptionPane.setText(text);

				return commandPositive.replace("#", textParam.getOnlyParam());
			}
		});

		// title
		titleParam.setInputValueExploitable(true);
		titleParam.setExecution(new Execution() {

			@Override
			public Object exec() {

				// prendo il valore da input che in questo caso sarà
				// il testo dell'alert
				String title = titleParam.getInputValue();

				joptionPane.setTitle(title);

				return commandPositive.replace("#", titleParam.getOnlyParam());
			}
		});

		iconParam.setInputValueExploitable(true);
		iconParam.setExecution(new Execution() {

			@Override
			public Object exec() {
				if (new File(iconParam.getInputValue()).exists()) {
					ImageIcon icon = new ImageIcon(iconParam.getInputValue());
					joptionPane.setIcon(icon);
					return commandPositive.replace("#", iconParam.getOnlyParam());
				} else {
					return "The file dosn't exist #";
				}
			}
		});

		typeParam.setInputValueExploitable(true);
		typeParam.setExecution(new Execution() {

			@Override
			public Object exec() {

				String valueMessageType = typeParam.getInputValue();
				boolean valid = false;
				switch (valueMessageType) {

				case "info":

					joptionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);

					valid = true;

					break;

				case "warning":

					joptionPane.setMessageType(JOptionPane.WARNING_MESSAGE);

					valid = true;

					break;

				case "error":

					joptionPane.setMessageType(JOptionPane.ERROR_MESSAGE);

					valid = true;

					break;

				default:

					break;
				}

				if (valid) {
					return commandPositive.replace("#", typeParam.getOnlyParam());
				} else {
					return commandNegative;
				}
			}
		});

		showParam.setExecution(new Execution() {

			@Override
			public Object exec() {

				// qui mostriamo la finestra dialog window

				JOptionPane.showMessageDialog(null, joptionPane.getText(), joptionPane.getTitle(),
						joptionPane.messageType, joptionPane.getIcon());

				return commandPositive.replace("#", showParam.getOnlyParam());

			}
		});

		// open the file
		// questo parametro apre il file condiviso
		openParam.setExecution(new Execution() {

			@Override
			public Object exec() {
				if (FILE_COMMAND.getSharedObject() != null) {

					£.executeProgram((File) FILE_COMMAND.getSharedObject());

					return commandPositive.replace("#", openParam.getOnlyParam());
				} else {
					return commandNegativeAbsenceObject;
				}
			}
		});

	}

	private static class JGOptionPane implements Serializable {
		private static final long serialVersionUID = 1L;
		private ImageIcon icon = null;
		private String text = null;
		private String title = null;
		private int messageType = 0;

		public ImageIcon getIcon() {
			return icon;
		}

		public void setIcon(ImageIcon icon) {
			this.icon = icon;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public int getMessageType() {
			return messageType;
		}

		public void setMessageType(int messageType) {
			this.messageType = messageType;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "title :" + this.title + " - Text :" + this.text + " - Icon :" + this.icon.getDescription()
					+ " - type :" + this.messageType;
		}
	}

}
