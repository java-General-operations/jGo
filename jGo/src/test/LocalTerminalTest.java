package test;

import cloud.jgo.jjdom.dom.nodes.xml.XMLDocument;
import cloud.jgo.jjdom.dom.nodes.xml.XMLElement;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.terminal.LocalTerminal;

public class LocalTerminalTest {
public static void main(String[] args) {
	
	
	LocalTerminal t = new LocalTerminal();
	
	t.setName("mio terminale");
	
	t.useGeneralHelp();
	
	LocalCommand.setInputHelpExploitable(true);
	
	// okok mi creo i comandi e inoltre mi serve un oggetto da condividere 
	
	
	LocalCommand create = new LocalCommand("create","creates a node");
	
	XMLDocument document = new XMLDocument();
	XMLElement rootElement = document.createElement("root");
	// aggiungo l'elemento 
	document.appendChild(rootElement);
	
	create.shareObject(rootElement,create);
	
	t.addCommand(create);
	
	// attivo il terminale 
	
	t.open();
	
	
	
}
}
