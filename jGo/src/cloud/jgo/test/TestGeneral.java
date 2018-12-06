package cloud.jgo.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import cloud.jgo.£;
import cloud.jgo.jjdom.dom.nodes.xml.XMLDocument;

public class TestGeneral {
public static void main(String[] args) throws UnknownHostException {
	
	
	InetAddress remoteAddress = InetAddress.getByName("localhost");
	InetAddress localAddress = InetAddress.getLocalHost();
	
	System.out.println(remoteAddress.getHostAddress());
	System.out.println(localAddress.getHostAddress());
	
	
}
}
