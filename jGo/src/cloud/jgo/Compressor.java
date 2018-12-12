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
package cloud.jgo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 
 * @author Martire91<br>
 *         This class deals with compressing files and folders
 */
public class Compressor {
	private ZipOutputStream zipOut = null;
	private java.io.File file;

	/**
	 * This method returns the file
	 * 
	 * @return the file
	 */
	public java.io.File getFile() {
		return file;
	}

	/**
	 * This method sets the file
	 * 
	 * @param file
	 *            the file
	 */
	public void setFile(java.io.File file) {
		this.file = file;
	}

	/**
	 * This method returns the archive name
	 * 
	 * @return archive name
	 */
	public String getArchiveName() {
		return archiveName;
	}

	/**
	 * This method sets the archive name
	 * 
	 * @param archiveName
	 *            the archive name
	 */
	public void setArchiveName(String archiveName) {
		this.archiveName = archiveName;
	}

	private String archiveName;

	public Compressor(String archiveName, File file) throws IOException {
		if (file != null) {
			this.file = file;
		}

		this.archiveName = archiveName;

		this.archiveName = this.archiveName + ".zip";
		// okok abbiamo preparatp il nome dell'archivio
		if (file != null) {
			File zip = new File(this.file.getParent() + File.separator + this.archiveName);
			zipOut = new ZipOutputStream(new FileOutputStream(zip));
		} else {
			// qui vuol dire che non si è impostato ne un file e ne una cartella per cui
			// quindi si presume che l'utente usufruirà del metodo
			// compressFiles(File[]files)
			zipOut = new ZipOutputStream(new FileOutputStream(
					new File(System.getProperty("user.home") + File.separator + this.archiveName)));
		}
	}

	/**
	 * This method compresses a file
	 * 
	 * @return true if the file is compressed
	 */
	public boolean compressFile() {
		if (!this.file.isDirectory()) {
			// va bene
			try {
				zipOut.putNextEntry(new ZipEntry(this.file.getName()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(this.file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			byte[] buffer = new byte[1024];
			BufferedInputStream in = new BufferedInputStream(fis);
			int leggi;
			try {
				while ((leggi = in.read(buffer, 0, buffer.length)) > -1) {
					zipOut.write(buffer, 0, leggi);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				zipOut.closeEntry();
				zipOut.flush();
				zipOut.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		} else {
			return false;
		}

	}

	/**
	 * This method compresses a files
	 * 
	 * @param files
	 *            the files
	 */
	public void compressFiles(File[] files) {
		for (int i = 0; i < files.length; i++) {
			if (!files[i].isDirectory()) {

				try {
					zipOut.putNextEntry(new ZipEntry(files[i].getName()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(files[i]);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				BufferedInputStream in = new BufferedInputStream(fis);
				byte[] buffer = new byte[1024];
				int leggi;
				try {
					while ((leggi = in.read(buffer, 0, buffer.length)) > -1) {
						zipOut.write(buffer, 0, leggi);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try {
			this.zipOut.closeEntry();
			this.zipOut.flush();
			this.zipOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ZipOutputStream getZipOut() {
		return zipOut;
	}

	/**
	 * This method compresses folder of only files
	 */
	public void compressFolderOfOnlyFiles() {
		File[] files = getFile().listFiles();
		ZipEntry entryRoot = null;
		entryRoot = new ZipEntry(files[0].getParentFile().getName());

		for (int i = 0; i < files.length; i++) {

			FileInputStream fis = null;
			try {
				fis = new FileInputStream(files[i]);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				zipOut.putNextEntry(new ZipEntry(entryRoot.getName() + "\\" + files[i].getName()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			byte[] buffer = new byte[1024];
			BufferedInputStream in = new BufferedInputStream(fis);
			int leggi;
			try {
				while ((leggi = in.read(buffer, 0, buffer.length)) > -1) {
					zipOut.write(buffer, 0, leggi);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					zipOut.closeEntry();
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try {
			zipOut.flush();
			zipOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method compresses a folder
	 * 
	 * @param files
	 *            the files folder
	 * @param entry
	 *            the entry
	 */
	public void compressFolder(File[] files, ZipEntry entry) {
		for (int i = 0; i < files.length; i++) {

			// qui controllo se si tratta di una cartella o file

			if (files[i].isDirectory()) {

				// ma nel caso in cui è una cartella
				// costruisco un altro entry che ha come path
				// l'entry precedente + il nome del file attuale che è una cartella e lo passo
				// al metodo

				ZipEntry folder = new ZipEntry(entry.getName() + File.separator + files[i].getName());

				compressFolder(files[i].listFiles(), folder);

			} else {
				// is a file

				// questo si basa sull'entry e si aggiunge solo il nome del file
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(files[i]);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					zipOut.putNextEntry(new ZipEntry(entry.getName() + File.separator + files[i].getName()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				byte[] buffer = new byte[1024];
				BufferedInputStream in = new BufferedInputStream(fis);
				int leggi;
				try {
					while ((leggi = in.read(buffer, 0, buffer.length)) > -1) {
						zipOut.write(buffer, 0, leggi);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						zipOut.closeEntry();
						fis.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
	}
}