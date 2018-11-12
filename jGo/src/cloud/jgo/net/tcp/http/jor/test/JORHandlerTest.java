package cloud.jgo.net.tcp.http.jor.test;

import java.util.Map;

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
import cloud.jgo.net.tcp.http.mime.MimeTypeFactory;
@HTTPMainPage(filesNames = { "index.html" })
public class JORHandlerTest extends JORHandlerConnection{

	@Override
	protected void organizesObjectsRootPage(Map<Object, String> structure,
			HTTPResponse response, String originalUrlPattern) {
		// TODO Auto-generated method stub
		
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
		h1.setTextContent("La password dell'account è "+a.getPassword());
		
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
