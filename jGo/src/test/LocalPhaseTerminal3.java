package test;

import cloud.jgo.£;
import cloud.jgo.utils.command.Command;
import cloud.jgo.utils.command.CommandsStore;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Parameter;
import cloud.jgo.utils.command.Sharer;
import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.execution.SharedExecution;
import cloud.jgo.utils.command.execution.Executable.When;
import cloud.jgo.utils.command.terminal.phase.LocalPhaseTerminal;
import cloud.jgo.utils.command.terminal.phase.Phase;
import cloud.jgo.utils.command.terminal.phase.Rule;

public class LocalPhaseTerminal3 {
public static void main(String[] args) {
	
	// controllo di esecuzione sulla fase 
	
	LocalPhaseTerminal t = new LocalPhaseTerminal("test-trm");
	
	t.useGeneralHelp();
	
	LocalCommand.setInputHelpExploitable(true);
	
	// mi creo la prima fase
	
	Phase phase = t.createPhase(1, "connection", "connection phase");
	phase.validExecution(When.IF_SATISFIED);
	phase.satisfiableThrough(new Rule() {
		
		@Override
		public boolean verification() {
			return false ;
		}
		
		@Override
		public String ruleExplanation() {
			// TODO Auto-generated method stub
			return "default";
		}
	});
	
	// mi creo i comandi di questa fase 
	
	LocalCommand connect = new LocalCommand("connect", "connect");
	connect.validExecution(When.IF_ACCESSIBLE);
	LocalCommand migrate = new LocalCommand("migrate", "migrate");
	migrate.validExecution(When.IF_ACCESSIBLE);
	LocalCommand download = new LocalCommand("download", "download");
	download.validExecution(When.IF_ACCESSIBLE);
	LocalCommand update = new LocalCommand("update", "update");
	update.validExecution(When.IF_ACCESSIBLE);
	
	
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

	
	// aggiungo i comandi alla fase
	
	t.addCommandsToPhase(phase, connect,migrate,download,update);
	
	
	t.open();
	
	
	
	
}
}
