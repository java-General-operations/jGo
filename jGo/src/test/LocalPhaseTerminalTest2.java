package test;

import cloud.jgo.utils.command.Command;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Sharer;
import cloud.jgo.utils.command.execution.Executable.When;
import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.execution.SharedExecution;
import cloud.jgo.utils.command.terminal.phase.LocalPhaseTerminal;
import cloud.jgo.utils.command.terminal.phase.Phase;


public class LocalPhaseTerminalTest2 {
public static void main(String[] args) {
	
	
	// 1 fare l'esecuzione del comando executor senza input value, quindi esegue la fase corrente
	// 2 creare un nuovo comando:status che fa un resoconto sulla fase attuale, e ci da quindi una serie di info utili
	// fine
	
	
	
	LocalPhaseTerminal t = new LocalPhaseTerminal("mio term");
	
	t.useGeneralHelp();
	
	LocalCommand.setInputHelpExploitable(true);
	
	// imposto
		
	
}
}
