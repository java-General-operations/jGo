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
package cloud.jgo.file_manager;

import java.awt.Component;
import java.io.File;
import java.util.Properties;

import javax.swing.JFileChooser;

/**
 * 
 * @author Martire91
 *
 */
public class JFileManager {
	private JFileChooser chooser = null;
	public final static String KEY_CURRENT_DIRECTORY = "com.jgo.file_manager.current_directory";
	public final static String KEY_TITLE = "com.jgo.file_manager.title";
	public final static String KEY_TEXT_APPROVE_BUTTON = "com.jgo.file_manager.text.approve_button";
	public final static String KEY_MULTI_SELECTION = "com.jgo.file_manager.multi_selection";
	private Properties props = new Properties();

	public JFileChooser getChooser() {

		return chooser;
	}

	public static final int MODE_OPEN = 0;
	public static final int MODE_SAVE = 1;

	public void setChooser(JFileChooser chooser) {
		this.chooser = chooser;
	}

	public JFileManager() {
		// TODO Auto-generated constructor stub
		this.chooser = new JFileChooser();
		this.chooser.setFileView(new JFileView());

	}

	public java.io.File setVisible(boolean value, int mode, Component parent) {

		if (this.chooser.isVisible() && value == false) {
			this.chooser.setVisible(value);
			return null;
		} else {
			if (mode == JFileManager.MODE_OPEN) {
				int result = this.chooser.showOpenDialog(parent);
				if (result == JFileChooser.APPROVE_OPTION) {
					return this.chooser.getSelectedFile();
				} else {
					this.chooser.setVisible(false);
					return null;
				}

			} else if (mode == JFileManager.MODE_SAVE) {
				int result = this.chooser.showSaveDialog(parent);
				if (result == JFileChooser.APPROVE_OPTION) {
					return this.chooser.getSelectedFile();
				} else {
					return null;
				}

			} else {
				return null;
			}
		}

	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
		if (props.containsKey(JFileManager.KEY_CURRENT_DIRECTORY)) {
			// imposto la directory
			this.chooser.setCurrentDirectory(new File(props.getProperty(JFileManager.KEY_CURRENT_DIRECTORY)));
		}
		if (props.containsKey(JFileManager.KEY_MULTI_SELECTION)) {
			// imposto la multi selezione
			String prop = this.props.getProperty(KEY_MULTI_SELECTION);
			if (prop.equals("true")) {
				this.chooser.setMultiSelectionEnabled(true);
			} else if (prop.equals("false")) {
				this.chooser.setMultiSelectionEnabled(false);
			}

		}
		if (props.containsKey(JFileManager.KEY_TEXT_APPROVE_BUTTON)) {
			// imposto il testo del button
			this.chooser.setApproveButtonText(props.getProperty(JFileManager.KEY_TEXT_APPROVE_BUTTON));
		}
		if (props.containsKey(JFileManager.KEY_TITLE)) {
			this.chooser.setDialogTitle(props.getProperty(JFileManager.KEY_TITLE));
		}

	}

}
