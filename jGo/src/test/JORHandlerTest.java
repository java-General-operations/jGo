package test;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.activation.MimeTypeParseException;

import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.HTMLElement;
import cloud.jgo.net.tcp.http.HTTPMainPage;
import cloud.jgo.net.tcp.http.HTTPResponse;
import cloud.jgo.net.tcp.http.HTTPServer;
import cloud.jgo.net.tcp.http.ResponseCode;
import cloud.jgo.net.tcp.http.Transport;
import cloud.jgo.net.tcp.http.headers.Header;
import cloud.jgo.net.tcp.http.headers.MimeHeader;
import cloud.jgo.net.tcp.http.jor.JORHandlerConnection;
import cloud.jgo.net.tcp.http.jor.JORServer;
import cloud.jgo.net.tcp.http.mime.MimeTypeFactory;
@HTTPMainPage(filesNames = {"home.html"})
public class JORHandlerTest extends JORHandlerConnection{

	@Override
	protected void organizesObjectsRootPage(Map<Object, String> structure,
			HTTPResponse response, String originalUrlPattern) {
		Iterator<Entry<Object, String>> iterator = structure.entrySet().iterator();
		response.setStatusLine(ResponseCode.RESPONSE_CODE_OK,JORServer.HTTP_VERSION);
		Header header = Header.getDefaultInstance(Header.Type.CONTENT_LENGTH);
		MimeHeader header2 = null ;
		try {
			header2 = new MimeHeader(MimeTypeFactory.getDefaultMimeType(MimeTypeFactory.FORMAT_HTML));
		} catch (MimeTypeParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("<html>"+Header.CRLF);
		buffer.append("<body>"+Header.CRLF);
		buffer.append("<strong>"+originalUrlPattern+" :</strong>"+Header.CRLF);
		while (iterator.hasNext()) {
			Map.Entry<java.lang.Object, java.lang.String> entry = (Map.Entry<java.lang.Object, java.lang.String>) iterator
					.next();
			Object obj = entry.getKey();
			String urlObj = entry.getValue();
			buffer.append("<ul>"+Header.CRLF);
			buffer.append("<li><a href='"+urlObj+"'>"+urlObj+"</a></li>"+Header.CRLF);
			buffer.append("</ul>"+Header.CRLF);
		}
		buffer.append("</body>"+Header.CRLF);
		buffer.append("</html>");
		
		int contentLen = buffer.toString().length();
		header.setValue(contentLen);
		response.addHeaders(header,header2);
		response.insertVoidRow();
		response.getBody().addBytes(buffer);
		response.getBody().ready();
		Transport.trasfer(response);
	}

	@Override
	protected void html_represents(Object obj, HTTPResponse res, String nameObj) {
		Account a = (Account) obj ;
		res.setStatusLine(ResponseCode.RESPONSE_CODE_OK,HTTPServer.HTTP_VERSION);
		
		// creo gli headers + importanti 
		
		Header contentLen = Header.getDefaultInstance(Header.Type.CONTENT_LENGTH);
		MimeHeader header = null ;
		try {
			header = new MimeHeader(MimeTypeFactory.getDefaultMimeType(MimeTypeFactory.FORMAT_HTML));
		} catch (MimeTypeParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// scrivo il markup html 
		JjDom.newDocument().useDoctype(true).setMinimalTags();
		
		HTMLElement h1,h2 = null ;
		
		h1 = JjDom.document.createElement(HTMLElement.HTMLElementType.H1);
		h2 = JjDom.document.createElement(HTMLElement.HTMLElementType.H2);
		
		h1.setTextContent("Account di "+a.getUsername());
		h1.setTextContent("La password dell'account e "+a.getPassword());
		
		JjDom.document.getBody().appendChilds(h1,h2);
		
		int len = JjDom.document.getMarkup().getBytes().length;
		contentLen.setValue(len);
		
		// aggiungo gli headers alla risposta 
		
		res.addHeaders(contentLen,header);
		
		res.insertVoidRow();
		
		res.getBody().addBytes(JjDom.document.getMarkup());
		
		res.getBody().ready();
		
		// invio la risposta 
		
		Transport.trasfer(res);
	}

}
