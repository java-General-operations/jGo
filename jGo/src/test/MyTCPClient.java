package test;

import java.io.IOException;

import cloud.jgo.£;
import cloud.jgo.net.Connection;
import cloud.jgo.net.tcp.TCPClient;
import cloud.jgo.utils.command.RemoteCommand;

public class MyTCPClient extends TCPClient{
	
	@Override
	public void communicates(Connection connection) throws IOException, ClassNotFoundException {
		while (true) {
			System.out.print("Command for server:");
			String command = £._I();
			connection.send(command);
		}
	}
	
}
