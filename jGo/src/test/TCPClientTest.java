package test;

import java.io.IOException;

import cloud.jgo.�;
import cloud.jgo.utils.command.Parameter;
import cloud.jgo.utils.command.RemoteCommand;
import cloud.jgo.utils.command.execution.Execution;

public class TCPClientTest {
public static void main(String[] args) throws IOException {
	
	
	MyTCPClient myClient = new MyTCPClient();
	
	// mi creo i comandi 
	
	RemoteCommand startCommand = new RemoteCommand("start","This command opens the windows cmd");
	RemoteCommand notepadCommand = new RemoteCommand("notepad","This command opens the windows notepad");
	RemoteCommand propsSystemCommand = new RemoteCommand("props","get the system properties");
	RemoteCommand openCommand = new RemoteCommand("open","This command opens some thing");
	
	
	startCommand.setInputValueExploitable(true);
	notepadCommand.setInputValueExploitable(true);
	
	
	Parameter startParam = openCommand.addParam("start","windows cmd");
	Parameter notepadParam = openCommand.addParam("notepad","windows notepad");
	
	startParam.setInputValueExploitable(true);
	notepadParam.setInputValueExploitable(true);
	
	startParam.setExecution(new Execution() {
		
		@Override
		public Object exec() {
			String inputValue = startParam.getInputValue();
			Integer intValue = Integer.parseInt(inputValue);
			�.openTerminal(intValue);
			return null ;
		}
	});
	
	notepadParam.setExecution(new Execution() {
		
		@Override
		public Object exec() {
			String inputValue = notepadParam.getInputValue();
			Integer intValue = Integer.parseInt(inputValue);
			�.openNotepad(intValue);
			return null ;
		}
	});
	
	startCommand.setExecution(new Execution() {
		
		@Override
		public Object exec() {
			if (startCommand.getInputValue()!=null) {
				int value = Integer.parseInt(startCommand.getInputValue());
				
				�.openTerminal(value);
				
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
				
				�.openNotepad(value);
				
				notepadCommand.setInputValue(null);	
			}
			
			
			return null ;
		}
	});
	
	propsSystemCommand.setExecution(new Execution() {
		
		@Override
		public Object exec() {
			
			return System.getProperties();
		}
	});
	
	
	myClient.addClientCommand(startCommand,notepadCommand,propsSystemCommand,openCommand);
	
	// mi connetto al server 
	boolean connected=myClient.connect("localhost",3332);
	
	if (connected) {
		System.out.println("Connessi al server @");
		myClient.startClient();
	}
	
	
	
	
}
}
