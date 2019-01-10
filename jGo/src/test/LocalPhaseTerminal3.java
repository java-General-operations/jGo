package test;

import cloud.jgo.£;
import cloud.jgo.utils.command.CommandsStore;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Parameter;
import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.execution.Executable.When;
import cloud.jgo.utils.command.terminal.phase.LocalPhaseTerminal;

public class LocalPhaseTerminal3 {
public static void main(String[] args) {
	
	// ora devo testare il fatto dell'esecuzione prima su comandi e parametri eppoi sulle fasi
	
	
	LocalPhaseTerminal t = new LocalPhaseTerminal("my trm");
	
	t.useGeneralHelp();
	
	LocalCommand.setInputHelpExploitable(true);
	
	
	// aggiungo qualche comando di prova
	
	LocalCommand command = new LocalCommand("open","opens");
	command.validExecution(When.NEVER); // quindi stiamo dicendo che il comando open non deve essere eseguito
	command.setExecution(new Execution() {
		
		@Override
		public Object exec() {
			// TODO Auto-generated method stub
			return "This command opens";
		}
	});
	Parameter p,p2,p3 ;
	p = command.addParam("cmd","windows cmd");
	p.validExecution(When.NEVER);
	p2 = command.addParam("notepad","windows notepad");
	p2.validExecution(When.NEVER);
	p3 = command.addParam("regedit","windows cmd");
	
	
	p.setExecution(new Execution() {
		
		@Override
		public Object exec() {
			
			return £.openTerminal();
		}
	});
	
	p2.setExecution(new Execution() {
		
		@Override
		public Object exec() {
			
			return £.openNotepad();
		}
	});
	
	p3.setExecution(new Execution() {
		
		@Override
		public Object exec() {
			
			return £.openRegedit();
		}
	});
	
	
	// aggiungo i comandi generali al terminale
	t.addCommands(command);
	
	
	t.open();
	
}
}
