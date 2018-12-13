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
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Parameter;
import cloud.jgo.utils.command.RemoteCommand;

public class MyTCPClient extends TCPClient{
	
	@Override
	public void communicates(Connection connection) throws IOException, ClassNotFoundException {
		TCPConnection conn = (TCPConnection) connection;
		// ottengo il comando che voglio inviare 
		RemoteCommand command = getCMD("open");
		// invio il comando
		conn.enter_cmd(command,"notepad","2");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn.enter_cmd(command,"start","3");
	
	}
}
