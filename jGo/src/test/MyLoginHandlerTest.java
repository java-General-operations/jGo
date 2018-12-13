package test;

import cloud.jgo.net.tcp.login.TCPLoginHandlerConnection;

public class MyLoginHandlerTest extends TCPLoginHandlerConnection{

	@Override
	public void doAccessGranted() {
		// TODO Auto-generated method stub
		String remoteInputMessage = (String) receive();
		System.out.println("Mex from client:"+remoteInputMessage);
	}

	@Override
	public void doAccessFailed() {
		// TODO Auto-generated method stub
		send("I'm sorry @");
	}

}
