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
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 * 
 * @author Martire91<br>
 * 
 *         This class downloads a file from the internet, showing the progress
 *         of the operation in a JProgressBar
 * 
 *
 */
public class DownloadWorker extends SwingWorker<Boolean, Integer> {
	private JProgressBar progress;
	private String url;
	private String fileName;

	public DownloadWorker(JProgressBar progress, String url, String fileNameDestination) {
		this.progress = progress;
		this.url = url;
		this.fileName = fileNameDestination;
		this.progress.setStringPainted(true);
		this.progress.setMinimum(0);
		this.progress.setValue(0);
	}

	@Override
	protected void process(List<Integer> chunks) {
		if (!isCancelled()) {
			int progr = chunks.get(chunks.size() - 1);
			this.progress.setValue(this.progress.getValue() + progr);
		}
	}

	@Override
	protected Boolean doInBackground() throws Exception {
		// qui in tanto devo verificare se l'url è esistente
		URLConnection connection = null;
		boolean downloaded = false;
		URL url_ = null;
		try {
			url_ = new URL(this.url);
			connection = url_.openConnection();
			progress.setMaximum(connection.getContentLength());
			InputStream input = null;
			try {
				input = connection.getInputStream();
				if (input != null) {
					byte[] buffer = new byte[1048576]; // 1 MB per sicurezza
					BufferedInputStream input_ = new BufferedInputStream(input);
					FileOutputStream fos = new FileOutputStream(fileName);
					int leggi;
					while ((leggi = input.read(buffer, 0, buffer.length)) > -1 || isCancelled()) {
						publish(leggi);
						fos.write(buffer, 0, leggi);
						Thread.sleep(new Random().nextInt(1000));
					}
					if (!isCancelled()) {
						fos.flush();
						fos.close();
						input_.close();
						downloaded = true;
					}
				}
			} catch (java.net.UnknownHostException e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error #", JOptionPane.ERROR_MESSAGE);
			}
		} catch (java.net.MalformedURLException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return downloaded;
	}

	@Override
	protected void done() {
		if (!isCancelled()) {
			try {
				System.err.println("(DONE) - Download completed :" + get().booleanValue());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// qui si può fare l'override per comunicare esempio l'avvenuto download
		}
	}
}
