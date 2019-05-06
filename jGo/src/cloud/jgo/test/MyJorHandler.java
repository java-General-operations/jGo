package cloud.jgo.test;

import java.util.Map;

import cloud.jgo.net.tcp.http.HTTPResponse;
import cloud.jgo.net.tcp.http.jor.JORHandlerConnection;

/**
 * @author Martire91 - <https://github.com/wasp91>
 * @version 1.0.0
 */
public class MyJorHandler extends JORHandlerConnection{
	
	// Bene sviluppare questo gestore di richieste JOR ...

	
	@Override
	protected void organizesObjectsRootPage(Map<Object, String> structure, HTTPResponse response,
			String originalUrlPattern) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	protected void html_represents(Object obj, HTTPResponse res, String nameObj) {
		// TODO Auto-generated method stub
		
	}

}
