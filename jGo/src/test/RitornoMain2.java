package test;

import cloud.jgo.£;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Sharer;
import cloud.jgo.utils.command.Sharer.Type;
import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.execution.SharedExecution;
import cloud.jgo.utils.command.execution.Executable.When;
import cloud.jgo.utils.command.terminal.LocalTerminal;
import cloud.jgo.utils.command.terminal.phase.LocalPhaseTerminal;
import cloud.jgo.utils.command.terminal.phase.Phase;
import cloud.jgo.utils.command.terminal.phase.Phase.PhaseExecutionType;
import cloud.jgo.utils.command.terminal.phase.PhaseExecution;
import cloud.jgo.utils.command.terminal.phase.PhaseGroup;
import cloud.jgo.utils.command.terminal.phase.Rule;

/**
 * @author Martire91 - <https://github.com/wasp91>
 * @version 1.0.0
 */
public class RitornoMain2 {
	private static boolean flag = false;

	public static void main(String[] args) {

		/*
		 * ###########################################################################
		 * 1) Adesso, devo aggiungere la feature .. questa è la prossima cosa da fare
		 * ###########################################################################
		 */
		// bene ultimiamo le fasi una volta per tutte

		// 1 passo : mi creo il terminale

		LocalPhaseTerminal t = new LocalPhaseTerminal("Mio");

		// 2 passo : indico che dovrà essere l'help generale ...

		t.useGeneralHelp();

		// 3 passo : indico che dovrà essere presente l'help di ogni comando

		LocalCommand.setInputHelpExploitable(true);

		// 4 passo : mi creo le fasi

		Phase startPhase, finalPhase, penultimatePhase, connectPhase, downloadPhase, updatePhase;
		// mi creo le altre fasi
		Phase openCmdPhase, openNotepadPhase, openRegeditPhase;

		// okok creo le fasi - 1 gruppo

		startPhase = t.createPhase(1, "start", "Fase iniziale");
		connectPhase = t.createPhase(2, "connect", "Connessione");
		downloadPhase = t.createPhase(3, "download", "Download");
		updatePhase = t.createPhase(4, "update", "Update");

		LocalPhaseTerminal.setCustomExecution(new MyPhaseExecution(t),startPhase,connectPhase,downloadPhase,updatePhase);
		
		t.open();
	}
	
	public static class MyPhaseExecution extends PhaseExecution{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * @param t
		 */
		public MyPhaseExecution(LocalPhaseTerminal t) {
			super(t);
			// imposto un flag
			setAsCurrentAfterExecution(false);
		}

		/* (non-Javadoc)
		 * @see cloud.jgo.utils.command.terminal.phase.PhaseExecution#sharedExec(cloud.jgo.utils.command.terminal.phase.Phase)
		 */
		@Override
		protected Object sharedExec(Phase phase) {
			// TODO Auto-generated method stub
			System.out.println("Esecuzione della fase "+phase.phaseName()+"...");
			return null ;
		}
		
	}
}
