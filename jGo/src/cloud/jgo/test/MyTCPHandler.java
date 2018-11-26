package cloud.jgo.test;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;

import cloud.jgo.net.tcp.TCPHandlerConnection;
import cloud.jgo.net.tcp.TCPServer;

public class MyTCPHandler extends TCPHandlerConnection{
	
	@Override
	public void manage() throws ClassNotFoundException, IOException,
			InterruptedException, HeadlessException, AWTException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public TCPServer getServer() {
		// TODO Auto-generated method stub
		return super.getServer();
	}

}
