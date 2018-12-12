package test;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.activation.MimeTypeParseException;

import cloud.jgo.net.tcp.http.HTTPMainPage;
import cloud.jgo.net.tcp.http.HTTPResponse;
import cloud.jgo.net.tcp.http.ResponseCode;
import cloud.jgo.net.tcp.http.Transport;
import cloud.jgo.net.tcp.http.headers.Header;
import cloud.jgo.net.tcp.http.headers.MimeHeader;
import cloud.jgo.net.tcp.http.jor.JORHandlerConnection;
import cloud.jgo.net.tcp.http.jor.JORServer;
import cloud.jgo.net.tcp.http.mime.MimeTypeFactory;
@HTTPMainPage(filesNames = { "index.html" })
public class MyJORServerHandlerTest extends JORHandlerConnection {

	@Override
	protected void organizesObjectsRootPage(Map<Object, String> structure, HTTPResponse response,
			String originalUrlPattern) {
		response.setStatusLine(ResponseCode.RESPONSE_CODE_OK,JORServer.HTTP_VERSION);
		Iterator<Entry<Object,String>>iterator = structure.entrySet().iterator();
		StringBuffer buffer = new StringBuffer();
		buffer.append("<ul>"+Header.CRLF);
		while (iterator.hasNext()) {
			Map.Entry<java.lang.Object, java.lang.String> entry = (Map.Entry<java.lang.Object, java.lang.String>) iterator
					.next();
			String link = entry.getValue();
			buffer.append("<li><a href='"+link+"'>"+link+"</a></li>"+Header.CRLF);
		}
		buffer.append("</ul>"+Header.CRLF);
		int content = buffer.toString().length();
		Header header,mimeHeader = null ;
		try {
			header = Header.getDefaultInstance(Header.Type.CONTENT_LENGTH,content);
			mimeHeader = new MimeHeader(MimeTypeFactory.getDefaultMimeType(MimeTypeFactory.FORMAT_HTML));
			response.addHeaders(header,mimeHeader);
			response.insertVoidRow();
			response.getBody().addBytes(buffer);
			response.getBody().ready();
			Transport.trasfer(response);
		} catch (MimeTypeParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	protected void html_represents(Object obj, HTTPResponse res, String nameObj) {
		// TODO Auto-generated method stub

	}

}
