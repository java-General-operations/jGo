package test;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import cloud.jgo.net.tcp.http.HTTPMainPage;
import cloud.jgo.net.tcp.http.HTTPResponse;
import cloud.jgo.net.tcp.http.jor.JORHandlerConnection;

public class MyJORServerHandlerTest extends JORHandlerConnection {

	@Override
	protected void organizesObjectsRootPage(Map<Object, String> structure, HTTPResponse response,
			String originalUrlPattern) {
		Iterator<Entry<Object,String>>iterator = structure.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<java.lang.Object, java.lang.String> entry = (Map.Entry<java.lang.Object, java.lang.String>) iterator
					.next();
			System.out.println(entry);
		}
		
	}

	@Override
	protected void html_represents(Object obj, HTTPResponse res, String nameObj) {
		// TODO Auto-generated method stub

	}

}
