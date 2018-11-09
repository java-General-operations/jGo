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
import java.awt.Component;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

public class TreeIconRenderer extends DefaultTreeCellRenderer{
	private JTree tree ;
	private String percorsoCartellaCheContieneCartellaPrincipale ;
	
	public TreeIconRenderer(String path,JTree tree) {
		// TODO Auto-generated constructor stub
	this.percorsoCartellaCheContieneCartellaPrincipale = path;
	this.tree = tree;
	
	}
	
	@Override
	public Component getTreeCellRendererComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4,
			int arg5, boolean arg6) {
		// TODO Auto-generated method stub
		super.getTreeCellRendererComponent(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
		
		DefaultMutableTreeNode nodoInQuestione = (DefaultMutableTreeNode)arg1;
		TreePath pathNode = new TreePath(nodoInQuestione.getUserObjectPath());
		String[]pathString = new String[nodoInQuestione.getUserObjectPath().length];
		// qui elimino gli spazi iniziali e finali di ogni pezzo del path
		for (int i = 0; i < pathString.length; i++) {
			pathString[i]=nodoInQuestione.getUserObjectPath()[i].toString().trim()+"\\";
		}
		// vedo se l'ultimo elemento è un file,in tal caso sostituisco lo slash finale
		String lastElement = pathString[pathString.length-1];
		if(lastElement.contains(".")==true){
			lastElement = lastElement.replace("\\", "");
			pathString[pathString.length-1]=lastElement;
		}
		
		
		// qui ottengo la stringa 
		String pathDaModificare = "";
		for (int i = 0; i < pathString.length; i++) {
			if(i ==0){
				pathDaModificare = pathString[i];
			}
			else{
				pathDaModificare = pathDaModificare+pathString[i];
			}
		}
		
		String pathOriginal = percorsoCartellaCheContieneCartellaPrincipale+pathDaModificare;
		File file = new File(pathOriginal);
		if(file.exists()){
			Icon icon = FileSystemView.getFileSystemView().getSystemIcon(file);
			this.tree.setToolTipText("File Local :"+file.getName());
			setIcon(icon);
		}
		return this ;
	}
	
	
	
	
	
}
