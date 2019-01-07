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
	
	// ottengo il comando tramite un metodo specifico 
	
	LocalCommand personCommand = LocalCommand.getCommandByObject(Person.class);
	
	
	// aggiungo il comando al terminale 
	
	t.addCommand(personCommand);
	
	t.open();
	
	
	
	
	
	
}
}
