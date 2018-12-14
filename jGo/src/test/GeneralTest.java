package test;

import org.fusesource.jansi.Ansi.Color;

import cloud.jgo.£;
import cloud.jgo.utils.ColorString;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Parameter;
import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.terminal.LocalTerminal;
import cloud.jgo.utils.command.terminal.phase.LocalPhaseTerminal;
import cloud.jgo.utils.command.terminal.phase.Phase;
import cloud.jgo.utils.command.terminal.phase.PhasesFactory;

public class GeneralTest {
	public static void main(String[] args) {

		// adesso dobbiamo capire il terminale di fasi, cosi da predisporre
		// jGo, per lo sviluppo di DomTerminal
		
		// vediamo di correggere ad uno a uno tutti i bugs del LocalPhaseTerminal 
		// prima di cominciare a lavorarci seriamente
		
		// 1 bug da risolvere, non deve essere per forza il simbolo di jGo 
		// come richiesta di comando
		
		
		LocalPhaseTerminal terminal = new LocalPhaseTerminal();
		terminal.setName(£.colors("DomT4j", Color.CYAN));
		terminal.useGeneralHelp();
		Phase phaseVOID = terminal.createPhase(1, £.colors("START",Color.MAGENTA), "Fase iniziale");
		Phase phaseCompile = terminal.createPhase(2, "compile", "compile application");
		Phase phaseDeploy = terminal.createPhase(3, "deploy", "deploy application");
		
		// mi creo i comandi della seconda fase
		// 2 fase 
		LocalCommand commandFolder = new LocalCommand("src","dir sources");
		LocalCommand comp = new LocalCommand("comp","compile");
		commandFolder.setInputValueExploitable(true);
		commandFolder.setInputHelpExploitable(true);
		commandFolder.setExecution(new Execution() {
			
			@Override
			public Object exec() {
				
				String inputValue = commandFolder.getInputValue();
				
				System.out.println("dir-src is set:"+inputValue);
				
				return null ;
			}
		});
		
		
		comp.setExecution(new Execution() {
			
			@Override
			public Object exec() {
				// TODO Auto-generated method stub
			
				System.out.println("comilazione in corso ...\nApplicazione compilata@");
				
				// si passa alla prossima phase 
				
				terminal.changePhase(terminal.nextPhase());
				
				return null ;
			}
		});
		
		terminal.addCommandsToPhase(phaseCompile, commandFolder,comp);
		
		
		
		terminal.open();
		
		
		
		
	}
}
