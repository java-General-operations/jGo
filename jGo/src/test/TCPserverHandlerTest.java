package test;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.Properties;

import cloud.jgo.net.tcp.TCPHandlerConnection;
import cloud.jgo.utils.command.RemoteCommand;

public class TCPserverHandlerTest extends TCPHandlerConnection{

	@Override
	public void manage()
			throws ClassNotFoundException, IOException, InterruptedException, HeadlessException, AWTException {
		// TODO Auto-generated method stub
		while(true) {
			String mexFromClient = (String) receive();
			Object objectReturned = RemoteCommand.executeInputCommand(mexFromClient,getClientCommands());
			send(objectReturned);
		}
	}

}
