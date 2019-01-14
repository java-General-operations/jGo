package test;

import cloud.jgo.jjdom.dom.nodes.xml.XMLDocument;
import cloud.jgo.jjdom.dom.nodes.xml.color.XMLColorDocument;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Parameter;
import cloud.jgo.utils.command.terminal.LocalTerminal;
import cloud.jgo.utils.command.terminal.Terminal;
import cloud.jgo.utils.command.terminal.phase.LocalPhaseTerminal;

public class ConfigurationCommandObject {
public static void main(String[] args) {
	
	
		
	LocalTerminal terminal = new LocalTerminal();

	terminal.useGeneralHelp();
	
	LocalCommand.setInputHelpExploitable(true);
	
	// okok mi creo i comandi :
	
	LocalCommand personCmd = LocalCommand.getCommandByType(Persona.class);
//	LocalCommand documentCmd = LocalCommand.getCommandByObject(XMLDocument.class);
	
	
	// aggiungo i comandi al terminale 
	
	terminal.addCommands(personCmd);
	
	terminal.open();
		
}
}
