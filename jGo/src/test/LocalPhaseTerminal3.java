package test;

import cloud.jgo.utils.command.CommandsStore;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.execution.Executable.When;
import cloud.jgo.utils.command.terminal.phase.LocalPhaseTerminal;

public class LocalPhaseTerminal3 {
public static void main(String[] args) {
	
	
	
	LocalPhaseTerminal t = new LocalPhaseTerminal("my trm");
	
	t.useGeneralHelp();
	
	LocalCommand.setInputHelpExploitable(true);
	
	
	// aggiungo qualche comando di prova
	
	// adesso voglio che questi comandi non vengano eseguiti mai
	
	LocalCommand command1 = CommandsStore.OPEN_COMMAND;
	LocalCommand command2 = CommandsStore.ALERT_COMMAND;
	
	command1.validExecution(When.NEVER);
	command2.validExecution(When.NEVER);
	
	// aggiungo i comandi al terminale in tanto 
	
	t.addCommands(command1,command2);
	
	
	t.open();
	
	
	
	
}
}
