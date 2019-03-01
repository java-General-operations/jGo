package test;

import cloud.jgo.£;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.terminal.phase.LocalPhaseTerminal;
import cloud.jgo.utils.command.terminal.phase.Phase;
import cloud.jgo.utils.command.terminal.phase.Phase.PhaseExecutionType;
import cloud.jgo.utils.command.terminal.phase.PhaseGroup;

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

		Phase startPhase, finalPhase, penultimatePhase, connectPhase, downloadPhase, updatePhase;

		// mi creo le altre fasi
		Phase openCmdPhase, openNotepadPhase, openRegeditPhase;

		// okok creo le fasi - 1 gruppo

		startPhase = t.createPhase(1, "start", "Fase iniziale");
		connectPhase = t.createPhase(2, "connect", "Connessione");
		downloadPhase = t.createPhase(3, "download", "Download");
		updatePhase = t.createPhase(4, "update", "Update");
		openCmdPhase = t.createPhase(5, "open_cmd", "Apre il terminale");
		openNotepadPhase = t.createPhase(6, "open_notepad", "Apre il notepad");
		openRegeditPhase = t.createPhase(7, "open_regedit", "Apre il registro");
		penultimatePhase = t.createPhase(8, "#end", "Penultima fase");
		finalPhase = t.createPhase(9, "end", "Fase finale");

		// stabilisco le esecuzioni personalizzate del primo gruppo di fasi

		startPhase.setExecution(new Execution() {

			@Override
			public Object exec() {

				return "Fase iniziale eseguita @";

			}
		}, PhaseExecutionType.CUSTOM);

		connectPhase.setExecution(new Execution() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Object exec() {
				// TODO Auto-generated method stub
				return "connessione eseguita @" + "";
			}
		}, PhaseExecutionType.CUSTOM);

		downloadPhase.setExecution(new Execution() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Object exec() {
				// TODO Auto-generated method stub
				return "Scaricamento eseguito @" + "";
			}
		}, PhaseExecutionType.CUSTOM);

		updatePhase.setExecution(new Execution() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Object exec() {
				// TODO Auto-generated method stub
				return "Aggiornamento eseguito @" + "";
			}
		}, PhaseExecutionType.CUSTOM);

		penultimatePhase.setExecution(new Execution() {

			@Override
			public Object exec() {

				return "Fase penultima eseguita @";

			}
		}, PhaseExecutionType.CUSTOM);

		finalPhase.setExecution(new Execution() {

			@Override
			public Object exec() {

				return "Fase finale eseguita @";

			}
		}, PhaseExecutionType.CUSTOM);

		// definisco le fasi del secondo gruppo :

		openCmdPhase.setExecution(new Execution() {

			@Override
			public Object exec() {

				return £.openTerminal();

			}
		}, PhaseExecutionType.CUSTOM);

		openNotepadPhase.setExecution(new Execution() {

			@Override
			public Object exec() {

				return £.openNotepad();

			}
		}, PhaseExecutionType.CUSTOM);

		openRegeditPhase.setExecution(new Execution() {

			@Override
			public Object exec() {

				return £.openRegedit();

			}
		}, PhaseExecutionType.CUSTOM);

		// fine delle esecuzioni

		// qui mi creo qualche comando che farà parte delle fasi del gruppo :
		// "root_executables"

		// comandi della fase open_cmd

		LocalCommand cmdCommand = new LocalCommand("cmd", "Apre il terminale");
		LocalCommand notepadCommand = new LocalCommand("notepad","Apre il notepad");
		LocalCommand regeditCommand = new LocalCommand("regedit","Apre il registro");
		cmdCommand.setInputValueExploitable(true);
		notepadCommand.setInputValueExploitable(true);
		regeditCommand.setInputValueExploitable(true);
		cmdCommand.setExecution(new Execution() {

			@Override
			public Object exec() {
				int times = Integer.parseInt(cmdCommand.getInputValue());
				£.openTerminal(times);
				return null;
			}
		});
		notepadCommand.setExecution(new Execution() {

			@Override
			public Object exec() {
				int times = Integer.parseInt(cmdCommand.getInputValue());
				£.openNotepad(times);
				return null;
			}
		});
		regeditCommand.setExecution(new Execution() {

			@Override
			public Object exec() {
				int times = Integer.parseInt(cmdCommand.getInputValue());
				£.openRegedit(times);
				return null;
			}
		});
		
		// aggiungo i comandi alle fasi apposite
		
		t.addCommandsToPhase(openCmdPhase,cmdCommand);
		t.addCommandsToPhase(openNotepadPhase, notepadCommand);
		t.addCommandsToPhase(openRegeditPhase, regeditCommand);
		
		// indico un gruppo di fasi - 1

		new PhaseGroup("online-management", connectPhase, downloadPhase, updatePhase);

		// indico un gruppo di fasi - 2

		new PhaseGroup("root_executables", openCmdPhase, openNotepadPhase, openRegeditPhase);

		// avvio il terminale

		t.open();

	}
}
