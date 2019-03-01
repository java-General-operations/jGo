package test;

import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.terminal.phase.LocalPhaseTerminal;
import cloud.jgo.utils.command.terminal.phase.Phase;
import cloud.jgo.utils.command.terminal.phase.Phase.PhaseExecutionType;

/**
 * @author Martire91 - <https://github.com/wasp91>
 * @version 1.0.0
 */
public class RitornoMain {
public static void main(String[] args) {
	
	
	// bene ultimiamo le fasi una volta per tutte 
	
	// 1 passo : mi creo il terminale 
	
	LocalPhaseTerminal t = new LocalPhaseTerminal("Mio");
	
	// 2 passo : indico che dovrà essere l'help generale ...
	
	t.useGeneralHelp();
	
	// 3 passo : indico che dovrà essere presente l'help di ogni comando 
	
	LocalCommand.setInputHelpExploitable(true);
	
	
	// 4 passo : mi creo le fasi 
	
	Phase startPhase, connectPhase, downloadPhase, updatePhase ;
	
	// okok creo le fasi 
	
	startPhase = t.createPhase(1, "start", "Fase iniziale");
	connectPhase = t.createPhase(2, "connect", "Connessione");
	downloadPhase = t.createPhase(3, "download", "Download");
	updatePhase = t.createPhase(4, "update", "Update");
	
	// stabilisco le esecuzioni personalizzate
	//, e dopo voglio fare un test con una esecuzione condivisa 
	
	connectPhase.setExecution(new Execution() {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public Object exec() {
			// TODO Auto-generated method stub
			return "connessione eseguita @"
					+ "";
		}
	},PhaseExecutionType.CUSTOM);
	
	downloadPhase.setExecution(new Execution() {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public Object exec() {
			// TODO Auto-generated method stub
			return "Scaricamento eseguito @"
			+ "";
		}
	},PhaseExecutionType.CUSTOM);
	
	
	updatePhase.setExecution(new Execution() {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public Object exec() {
			// TODO Auto-generated method stub
			return "Aggiornamento eseguito @"
			+ "";
		}
	},PhaseExecutionType.CUSTOM);
	
	
	// per il momento avvio il terminale 
	
	t.open();
	
	
}
}
