package test;

import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.terminal.phase.LocalPhaseTerminal;
import cloud.jgo.utils.command.terminal.phase.Phase;

public class LocalPhaseTerminalTest2 {
public static void main(String[] args) {
	
	
	
	
	// 1 se creiamo una fase tramite la factory, eppoi l'aggiungiamo al terminale in un qualche modo che succede
	// 2 vogliamo determinare se le fasi possono essere eseguite sempre oppure
	
	
	LocalPhaseTerminal terminal = new LocalPhaseTerminal("mio");
	
	terminal.useGeneralHelp(); // > help
	
	LocalCommand.setInputHelpExploitable(true); // > command help
			
	// mi creo una fase tramite la factory
	
	
	// adesso vediamo come posso creare una fase 
	
	
	
	
}
}
