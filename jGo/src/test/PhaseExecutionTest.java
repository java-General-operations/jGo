package test;

import cloud.jgo.jjdom.dom.nodes.xml.XMLDocument;
import cloud.jgo.jjdom.dom.nodes.xml.color.XMLColorDocument;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Parameter;
import cloud.jgo.utils.command.Sharer;
import cloud.jgo.utils.command.execution.Executable.When;
import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.execution.SharedExecution;
import cloud.jgo.utils.command.terminal.LocalTerminal;
import cloud.jgo.utils.command.terminal.Terminal;
import cloud.jgo.utils.command.terminal.phase.LocalPhaseTerminal;
import cloud.jgo.utils.command.terminal.phase.Phase;

public class PhaseExecutionTest {
public static void main(String[] args) {
	
	
	// a questo punto voglio verificare in tanto se
		// eseguo la 3 fase, esegue le due precedenti ??
	// Adesso verificare ancora presenza di bug
	// e dopodichè dobbiamo aggiornare lo stesso
	// metodo però della sotto classe: ColorLocalCommand
		
	LocalPhaseTerminal t = new LocalPhaseTerminal("trm");
	t.useGeneralHelp();
	LocalCommand.setInputHelpExploitable(true);
	
	// mi creo le fasi ...
	
	Phase start, connection, migration, download, update ;
	
	start = t.createPhase(1, "start", "inizio");
	start.validExecution(When.NEVER); // imposto che la 1 fase non vuole eseguirsi mai
	connection = t.createPhase(2, "connection", "connessione");
	connection.validExecution(When.ALWAYS);
	migration = t.createPhase(3, "migration", "migrazione");
	migration.validExecution(When.ALWAYS);
	download = t.createPhase(4, "download", "scaricamento");
	download.validExecution(When.ALWAYS);
	update = t.createPhase(5, "update", "aggiornamento");
	update.validExecution(When.ALWAYS);
	
	// bene adesso vogliamo attribuire a ognuna di questa fasi dei comandi
	// bene
	
	// commands :
	
	LocalCommand connectCommand, migrateCommand, downloadCommand, updateCommand;
	
	connectCommand = new LocalCommand("connect", "si connette");
	migrateCommand = new LocalCommand("migrate", "migra il documento");
	downloadCommand = new LocalCommand("download", "scarica il documento");
	updateCommand = new LocalCommand("update", "aggiorna il documento in rete");
	
	SharedExecution execution = new SharedExecution() {
		
		@Override
		protected Object sharedExec(Sharer sharer) {
			Object result = null ;
			if (sharer.getSharerType()==Sharer.Type.COMMAND) {
				LocalCommand source = (LocalCommand) sharer ;
				if (source.getCommand().equals("connect")) {
					result = "Connesso ( OK )";
				}
				else if(source.getCommand().equals("migrate")) {
					result = "Documento migrato ( OK )";
				}
				else if(source.getCommand().equals("download")) {
					result = "Documento scaricato ( OK )";
				}
				else if(source.getCommand().equals("update")) {
					result = "Documento aggiornato ( OK )";
				}
			}
			return result ;
		}
	};
	
	connectCommand.setExecution(execution);
	migrateCommand.setExecution(execution);
	downloadCommand.setExecution(execution);
	updateCommand.setExecution(execution);
	
	
	// aggiungo i comandi alle rispettive fasi
	
	t.addCommandsToPhase(connection, connectCommand);
	t.addCommandsToPhase(migration, migrateCommand);
	t.addCommandsToPhase(download, downloadCommand);
	t.addCommandsToPhase(update, updateCommand);
	
	
	// apro il terminale 
	
	t.open();
		
}
}
