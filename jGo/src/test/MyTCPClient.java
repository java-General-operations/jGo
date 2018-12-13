package test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import javax.swing.JOptionPane;
import cloud.jgo.£;
import cloud.jgo.net.Connection;
import cloud.jgo.net.tcp.TCPClient;
import cloud.jgo.net.tcp.TCPConnection;
import cloud.jgo.utils.command.RemoteCommand;

public class MyTCPClient extends TCPClient{
	
	@Override
	public void communicates(Connection connection) throws IOException, ClassNotFoundException {
		TCPConnection conn = (TCPConnection) connection;
		conn.send("start 2");
		ArrayList<Object>returnedValue = (ArrayList<Object>) conn.receive();
	}
	
}
