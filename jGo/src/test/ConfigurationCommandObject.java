package test;

import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.terminal.LocalTerminal;

public class ConfigurationCommandObject {
public static void main(String[] args) {
	
	// test corretto
	
	LocalTerminal terminal = new LocalTerminal();
	
	terminal.useGeneralHelp();
	
	LocalCommand.setInputHelpExploitable(true);
	
	// okok mi creo i comandi :
	
	LocalCommand personCmd = LocalCommand.getCommandByObject(Persona.class);
	
	
	// aggiungo i comandi al terminale 
	
	terminal.addCommand(personCmd);
	
	terminal.open();
	
	
	
	
	
}
}
