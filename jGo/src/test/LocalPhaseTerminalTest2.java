package test;

import cloud.jgo.utils.command.Command;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Sharer;
import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.execution.SharedExecution;
import cloud.jgo.utils.command.terminal.phase.LocalPhaseTerminal;
import cloud.jgo.utils.command.terminal.phase.Phase;
import cloud.jgo.utils.command.terminal.phase.Phase.When;


public class LocalPhaseTerminalTest2 {
public static void main(String[] args) {
	
	
	LocalPhaseTerminal terminal = new LocalPhaseTerminal("mio");
	
	terminal.useGeneralHelp(); // > help
	
	LocalCommand.setInputHelpExploitable(true); // > command help
			
	// creo una fase di test
	
	Phase phase = terminal.createPhase(1, "connection", "connection");
	
	//adesso mettiamo il caso che vogliamo eseguire la fase solo in base a un certo criterio :
	// in pratica abbiamo impostato che solo se la fase è soddisfatta si può eseguire 
	phase.validExecution(When.IF_SATISFIED);
	// mi creo i comandi di questa fase
	
	LocalCommand connect,migrate,download,update ;
	
	connect = new LocalCommand("connect", "connect");
	migrate = new LocalCommand("migrate", "migrate");
	download = new LocalCommand("download", "download");
	update = new LocalCommand("update", "update");
	
	SharedExecution execution = new SharedExecution() {
		
		@Override
		protected Object sharedExec(Sharer sharer) {
			
			if (sharer.getSharerType()==Sharer.Type.COMMAND) {
				
				Command source = (Command) sharer;
				
				return source.getCommand()+" successfully (OK)";							
			}
			else return null ;
		}
	};
	
	connect.setExecution(execution);
	migrate.setExecution(execution);
	download.setExecution(execution);
	update.setExecution(execution);
	
	// okok aggiungo i comandi alla fase
	
	terminal.addCommandsToPhase(phase, connect,migrate,download,update);
	
	terminal.open();
	
		
	
}
}
