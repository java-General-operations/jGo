package test;

import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.terminal.phase.LocalPhaseTerminal;
import cloud.jgo.utils.command.terminal.phase.Phase;

public class LocalPhaseTerminalTest2 {
public static void main(String[] args) {
	
	
	// poi devo vedere cosa succede se creo una fase senza passare dal terminale, quindi tramite factory esterna
	
	// 1 se creiamo una fase tramite la factory, eppoi l'aggiungiamo al terminale in un qualche modo che succede
	// 2 vogliamo determinare se le fasi possono essere eseguite sempre oppure
	
	
	LocalPhaseTerminal terminal = new LocalPhaseTerminal("mio");
	
	terminal.useGeneralHelp(); // > help
	
	LocalCommand.setInputHelpExploitable(true); // > command help
			
	// mi creo una fase tramite la factory
	
	
	// adesso vediamo come posso creare una fase
	Phase fase1 = terminal.createPhase(1, "connection", "connection phase");
	Phase fase2 = terminal.createPhase(2, "end", "end phase");
	
	
	// okok mi sono creato la fase, ora però mi creo i comandi che dovranno far parte di questa fase
	
	LocalCommand connect,migrate,download,update ;
	
	connect = new LocalCommand("connect", "connect");
	migrate = new LocalCommand("migrate", "migrate");
	download = new LocalCommand("download", "download");
	update = new LocalCommand("update", "update");
	
	
	// bene, adesso do una esecuzione a ciascuno, come se non avessimo l'esecuzione condivisa
	
	connect.setExecution(new Execution() {
		
		@Override
		public Object exec() {
			// TODO Auto-generated method stub
			return "Connect OK";
		}
	});
	
	migrate.setExecution(new Execution() {
		
		@Override
		public Object exec() {
			// TODO Auto-generated method stub
			return "Migrate OK";
		}
	});
	download.setExecution(new Execution() {
		
		@Override
		public Object exec() {
			// TODO Auto-generated method stub
			return "Download OK";
		}
	});
	update.setExecution(new Execution() {
		
		@Override
		public Object exec() {
			// TODO Auto-generated method stub
			return "Update OK";
		}
	});
	
	
	// bene a questo punto posso aggiungere i comandi alla fase tramite terminale
	
	terminal.addCommandsToPhase(fase1, connect,migrate,download,update);
	
	
	// bene posso attivare il terminale 
	
	terminal.open();
	
	
}
}
