package test;

import cloud.jgo.£;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Sharer;
import cloud.jgo.utils.command.Sharer.Type;
import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.execution.SharedExecution;
import cloud.jgo.utils.command.terminal.LocalTerminal;
import cloud.jgo.utils.command.terminal.phase.LocalPhaseTerminal;
import cloud.jgo.utils.command.terminal.phase.Phase;
import cloud.jgo.utils.command.terminal.phase.Phase.PhaseExecutionType;
import cloud.jgo.utils.command.terminal.phase.PhaseGroup;
import cloud.jgo.utils.command.terminal.phase.Rule;

/**
 * @author Martire91 - <https://github.com/wasp91>
 * @version 1.0.0
 */
public class RitornoMain {
	private static boolean flag = false;

	public static void main(String[] args) {

		/*
		 * ###########################################################################
		 * 2) Stabilire che in una fase, senza certa criteri non si ci può entrare 3) se
		 * una fase ha delle regole, può essere kmq eseguita da un altra fase ?
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

		// stabilisco l'esecuzione si start

		startPhase.setExecution(new Execution() {

			@Override
			public Object exec() {
				// TODO Auto-generated method stub
				return "Fase iniziale eseguita @";
			}
		}, PhaseExecutionType.CUSTOM);

		// stabilisco l'esecuzione condivisa del primo gruppo di fasi

		SharedExecution execution = new SharedExecution() {

			@Override
			protected Object sharedExec(Sharer sharer) {
				Phase phase = (Phase) sharer;
				if (phase.phaseName().equals("connect")) {
					flag = true;
					return "Connessione eseguita @";
				} else if (phase.phaseName().equals("download")) {
					return "Scaricamento eseguito @";
				} else if (phase.phaseName().equals("update")) {
					return "Aggiornamento eseguito @";
				} else {
					return null;
				}
			}
		};
		connectPhase.setExecution(execution, PhaseExecutionType.CUSTOM);
		downloadPhase.setExecution(execution, PhaseExecutionType.CUSTOM);
		updatePhase.setExecution(execution, PhaseExecutionType.CUSTOM);

		// aggiungo queste fasi ad un primo gruppo

		new PhaseGroup("online-management", connectPhase, downloadPhase, updatePhase);

		// adesso mi creo altre semplici fasi da inserire in un altro gruppo

		Phase ciaoPhase, helloPhase;

		ciaoPhase = t.createPhase(5, "ciao", "Saluta in Italiano");

		helloPhase = t.createPhase(6, "hello", "Saluta in Inglese");

		// indico una regola di accesso per questa fase, niente di più semplice :

		ciaoPhase.accessibleThrough(new Rule() {

			@Override
			public boolean verification() {
				// TODO Auto-generated method stub
				return flag;
			}

			@Override
			public String ruleExplanation() {
				return "soltanto se si eseguiti la connessione"; // per dire ...
			}
		});

		helloPhase.accessibleThrough(new Rule() {

			@Override
			public boolean verification() {
				// TODO Auto-generated method stub
				return ciaoPhase.isAccessible();
			}

			@Override
			public String ruleExplanation() {
				// TODO Auto-generated method stub
				return "Soltanto se si è entrati nell fase ciao";
			}
		});

		// mi creo una esecuzione condivisa

		SharedExecution execution2 = new SharedExecution() {

			@Override
			protected Object sharedExec(Sharer sharer) {
				Phase p = (Phase) sharer;
				if (p.phaseName().equals("ciao")) {
					return "Ciao !!";
				} else {
					return "Hello !!";
				}
			}
		};

		ciaoPhase.setExecution(execution2, PhaseExecutionType.CUSTOM);
		helloPhase.setExecution(execution2, PhaseExecutionType.CUSTOM);

		// creo un altro gruppo

		new PhaseGroup("test", ciaoPhase, helloPhase);

		// attivo il terminale

		t.open();

	}
}
