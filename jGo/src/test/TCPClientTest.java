package test;

import java.io.IOException;

import cloud.jgo.£;
import cloud.jgo.utils.command.RemoteCommand;
import cloud.jgo.utils.command.execution.Execution;

public class TCPClientTest {
public static void main(String[] args) throws IOException {
	
	
	MyTCPClient myClient = new MyTCPClient();
	
	// mi creo i comandi 
	
	RemoteCommand startCommand = new RemoteCommand("start","This command opens the windows cmd");
	RemoteCommand notepadCommand = new RemoteCommand("notepad","This command opens the windows notepad");
	
	startCommand.setInputValueExploitable(true);
	notepadCommand.setInputValueExploitable(true);
	
	startCommand.setExecution(new Execution() {
		
		@Override
		public Object exec() {
			if (startCommand.getInputValue()!=null) {
				int value = Integer.parseInt(startCommand.getInputValue());
				
				£.openTerminal(value);
				
				startCommand.setInputValue(null);

			}
			return null ;
		}
	});
	notepadCommand.setExecution(new Execution() {
		
		@Override
		public Object exec() {
			
			if (notepadCommand.getInputValue()!=null) {
				int value = Integer.parseInt(notepadCommand.getInputValue());
				
				£.openNotepad(value);
				
				notepadCommand.setInputValue(null);	
			}
			
			
			return null ;
		}
	});
	
	
	myClient.addClientCommand(startCommand,notepadCommand);
	
	// mi connetto al server 
	boolean connected=myClient.connect("localhost",3332);
	
	if (connected) {
		System.out.println("Connessi al server @");
		myClient.startClient();
	}
	
	
	
	
}
}
