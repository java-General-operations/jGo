package test;

import java.io.IOException;

import javax.activation.MimeTypeParseException;

import cloud.jgo.net.tcp.http.HTTPHandlerConnection;
import cloud.jgo.net.tcp.http.HTTPRequest;
import cloud.jgo.net.tcp.http.HTTPResponse;

public class HTTPServerHandlerTest extends HTTPHandlerConnection{

	@Override
	public void manage(HTTPRequest request, HTTPResponse response) throws IOException, ClassNotFoundException {
		try {
			response.basicConfig(HTTPServerHandlerTest.class);
		} catch (MimeTypeParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getSocket().close();
	}

}
