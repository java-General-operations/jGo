package cloud.jgo.test;


import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import cloud.jgo.net.tcp.http.HTTPResponse;
import cloud.jgo.net.tcp.http.HTTPServer;
import cloud.jgo.net.tcp.http.ResponseCode;
import cloud.jgo.net.tcp.http.jor.JORHandlerConnection;

/**
 * @author Martire91 - <https://github.com/wasp91>
 * @version 1.0.0
 */
public class MyJorHandler extends JORHandlerConnection{
	
	
	private static final long serialVersionUID = 1L;


	@Override
	protected void organizesObjectsRootPage(Map<Object, String> structure, HTTPResponse response,
			String originalUrlPattern) {
		
		// imposto la risposta http JOR
		
		response.setStatusLine(ResponseCode.RESPONSE_CODE_OK,HTTPServer.HTTP_VERSION);
		
		// devo continuare da qui impostando gli headers...
		
		Iterator<Entry<Object, String>>entries = structure.entrySet().iterator();
		
		while (entries.hasNext()) {
			Map.Entry<java.lang.Object, java.lang.String> entry = (Map.Entry<java.lang.Object, java.lang.String>) entries
					.next();
			String linkObject = entry.getValue();
			Book book = (Book) entry.getKey();
			
		}
		
	}

	
	@Override
	protected void html_represents(Object obj, HTTPResponse res, String nameObj) {
		// TODO Auto-generated method stub
		
	}

}
