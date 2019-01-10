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
	
	// controllo di esecuzione sulla fase :
	// adesso vogliamo provare a bloccare l'esecuzione
	// di un comando e tutti i suoi parametri, fin quando
	// la fase non risulta soddisfatta
	
	LocalPhaseTerminal t = new LocalPhaseTerminal("test-trm");
	
	t.useGeneralHelp();
	
	LocalCommand.setInputHelpExploitable(true);
	
	// mi creo i comandi:
	
		LocalCommand objCommand = LocalCommand.getCommandByObject(Persona.class);
		LocalCommand blocked = new LocalCommand("blocked","blocked help");
		// qui imposto che questo comando deve essere eseguito solo
		// quando la fase a cui è associato è soddisfatta
		blocked.validExecution(When.IF_SATISFIED);
		blocked.setExecution(new Execution() {
			
			@Override
			public Object exec() {
				// TODO Auto-generated method stub
				return "blocked execution";
			}
		});
		// mi creo qualche parametro di blocked
		Parameter p,p2 ;
		p = blocked.addParam("a","a help");
		p2 = blocked.addParam("b","b help");
		p.validExecution(When.IF_SATISFIED);
		p2.validExecution(When.IF_SATISFIED);
		
		p.setExecution(new Execution() {
			
			@Override
			public Object exec() {
				// TODO Auto-generated method stub
				return "a execution";
			}
		});
		
		p2.setExecution(new Execution() {
			
			@Override
			public Object exec() {
				// TODO Auto-generated method stub
				return "b execution";
			}
		});
	
	// mi creo la prima fase
	
	Phase phase = t.createPhase(1, "creation", "creation phase");
	phase.satisfiableThrough(new Rule() {
		
		@Override
		public boolean verification() {
			if (objCommand.getSharedObject()!=null) {
				Persona persona = objCommand.getSharedObject();
				if (persona.getNome()!=null&&persona.getCognome()!=null&&persona.getEtà()>18)return true ;
				else return false ;
			}
			else return false ;
		}
		
		@Override
		public String ruleExplanation() {
			// TODO Auto-generated method stub
			return "Se la persona è stata configurata correttamente";
		}
	});
	
	// aggiungo i comandi alla fase
	
	t.addCommandsToPhase(phase, objCommand,blocked);
	
	
	t.open();
	
	
	
	
}
}
