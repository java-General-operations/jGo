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
		while (true) {
			System.out.print("Command for server:");
			String command = £._I();
			conn.send(command);
			// ricevo la risposta 
			Object objReturned = conn.receive();
			// sappiamo che è un arraylist
			if (objReturned!=null) {
				ArrayList<Object>listObjs = (ArrayList<Object>) objReturned;
				Iterator<Object>iterator = listObjs.iterator();
				while (iterator.hasNext()) {
					Object object = (Object) iterator.next();
					if (object!=null) {
						// diamo per scontato che sono proprietà
						Properties remoteProps = (Properties) object;
						remoteProps.list(System.out);
					}
				}	
			}
		}
	}
	
}
