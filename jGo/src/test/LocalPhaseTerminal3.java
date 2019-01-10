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
	
	
	// okok adesso mi creo una bella fase
	
	Phase phase = t.createPhase(1, "connection", "connection phase");
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
	LocalCommand blocked = new LocalCommand("blocked","Blocked");
	blocked.validExecution(When.IF_SATISFIED);
	
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
	blocked.setExecution(execution);
	
	// aggiungo i comandi alla fase
	
	t.addCommandsToPhase(phase, connect,migrate,download,update,blocked);
	
	
	t.open();
	
	
}
}
