package cloud.jgo.test;

import java.util.List;

import cloud.jgo.net.config.Configuration2;
import cloud.jgo.net.config.ServerConfiguration2;
import cloud.jgo.net.config.TCPServerConfiguration2;

public class ConfigTest {
public static void main(String[] args) {
	
	
	
	ServerConfiguration2 config2 = new TCPServerConfiguration2();
	
	config2.put(TCPServerConfiguration2.MAXIMUM_SOCKETS,10);
	
	
	
	
	
	
	
}
}
