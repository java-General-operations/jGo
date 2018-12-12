/**
 * JGO - A pure Java library,
 * its purpose is to make life easier for the programmer.
 *
 * J - Java
 * G - General
 * O - Operations
 *
 * URL Software : https://www.jgo.cloud/
 * URL Documentation : https://www.jgo.cloud/docs/
 *
 * Copyright © 2018 - Marco Martire (www.jgo.cloud)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License.
 *
 * You may obtain a copy of License at :
 * https://www.jgo.cloud/LICENSE.txt
 *
 * To collaborate on this project, you need to do it from the software site.
 * 
 */
package cloud.jgo.downloads;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 
 * @author Martire91
 *
 */
public class Download extends Thread {

	private URL url;
	private String pathDestination;
	public static final int PROTO_HTTP = 0, PROTO_HTTPS = 1;
	private String downloadOK;
	private String downloadNO;

	public String getDownloadOK() {
		return downloadOK;
	}

	public void setDownloadOK(String downloadOK) {
		this.downloadOK = downloadOK;
	}

	public String getDownloadNO() {
		return downloadNO;
	}

	public void setDownloadNO(String downloadNO) {
		this.downloadNO = downloadNO;
	}

	public Download(URL url, String pathDestination) {
		// TODO Auto-generated constructor stub
		this.pathDestination = pathDestination;
		setName("Download");
		this.url = url;
	}

	public Download() {
		setName("Download");
		this.url = null;
		this.pathDestination = null;
	}

	public String getPathDestination() {
		return pathDestination;
	}

	public void setPathDestination(String pathDestination) {
		this.pathDestination = pathDestination;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	@Override
	public void run() {
		try {
			download();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method downloads a file from the internet
	 */
	private void download() throws IOException {
		URLConnection connection = this.url.openConnection();
		byte[] buffer = new byte[1024];
		BufferedInputStream fis = new BufferedInputStream(connection.getInputStream());
		FileOutputStream fos = new FileOutputStream(new File(pathDestination));
		int leggi;
		System.out.println("Wait for ...");
		while ((leggi = fis.read(buffer, 0, buffer.length)) > -1) {
			fos.write(buffer, 0, leggi);
		}
		fos.flush();
		fos.close();
		if (new File(pathDestination).exists()) {
			System.out.println(downloadOK);
		} else {
			System.err.println(downloadNO);
		}
	}

}
