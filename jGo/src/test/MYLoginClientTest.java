package test;

import java.io.IOException;
import java.util.Scanner;

import cloud.jgo.£;
import cloud.jgo.net.tcp.login.TCPLoginClient;

public class MYLoginClientTest extends TCPLoginClient{
	
	public static void main(String[] args) throws IOException {
		MYLoginClientTest client = new MYLoginClientTest();
		client.setReadFrom(System.out);
		client.setWriteFrom(new Scanner(System.in));
		boolean connected=client.connect("localhost",3332);
		if (connected) {
			client.startClient();
		}
	}

	@Override
	public void doAccessGranted() {
		System.out.print("Mexg for server :");
		String inputMessage = £._I();
		getConnection().send(inputMessage);
		
	}
	@Override
	public void doAccessFailed() {
		// TODO Auto-generated method stub
		
	}
}
