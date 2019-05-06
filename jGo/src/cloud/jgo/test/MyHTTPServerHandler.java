package cloud.jgo.test;

import java.io.IOException;

import javax.activation.MimeTypeParseException;

import cloud.jgo.net.tcp.http.HTTPHandlerConnection;
import cloud.jgo.net.tcp.http.HTTPMainPage;
import cloud.jgo.net.tcp.http.HTTPRequest;
import cloud.jgo.net.tcp.http.HTTPResponse;

/**
 * @author Martire91 - <https://github.com/wasp91>
 * @version 1.0.0
 */
@HTTPMainPage(filesNames = { "index.html" })
public class MyHTTPServerHandler extends HTTPHandlerConnection{

	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see cloud.jgo.net.tcp.http.HTTPHandlerConnection#manage(cloud.jgo.net.tcp.http.HTTPRequest, cloud.jgo.net.tcp.http.HTTPResponse)
	 */
	@Override
	public void manage(HTTPRequest request, HTTPResponse response) throws IOException, ClassNotFoundException {
		
		try {
			response.basicConfig(MyHTTPServerHandler.class);
		} catch (MimeTypeParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
