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
import cloud.jgo.utils.command.terminal.LocalTerminal;
import cloud.jgo.utils.command.terminal.phase.LocalPhaseTerminal;
import cloud.jgo.utils.command.terminal.phase.Phase;
import cloud.jgo.utils.command.terminal.phase.Rule;

public class LocalPhaseTerminal3 {
public static void main(String[] args) {
	
	// In pratica ci deve essere una classe che facilita questa cosa
	
	// per esempio una ConfObject - > oggetto configurazione che mette a disposizione i due metodi
	
	// 1 target : l'obbiettivo della configurazione sottoforma di stringa, quindi cosa si sta configurando
	
	// 2 isCompleted : indica se a la conf dell'oggetto è configurata o meno
	
	LocalTerminal terminal = new LocalTerminal();
	
	terminal.setName("frx");
	
	terminal.useGeneralHelp();
	
	LocalCommand.setInputHelpExploitable(true);
	
	
	// adesso mi creo solo un comando
	
	LocalCommand personCmd = new LocalCommand("persona","Crea una persona");
	
	Parameter newParam,nomeParam,cognomeParam,etaParam ;
	
	newParam = personCmd.addParam("new","crea una instanza");
	nomeParam = personCmd.addParam("nome","Imposta il nome");
	cognomeParam = personCmd.addParam("cognome","Imposta il cognome");
	etaParam = personCmd.addParam("eta","Imposta l'età");
	
	nomeParam.setInputValueExploitable(true);cognomeParam.setInputValueExploitable(true);etaParam.setInputValueExploitable(true);
	
	// okok creo una esecuzione condivisa per quadagnare codice
	
	SharedExecution execution = new SharedExecution() {
		
		@Override
		protected Object sharedExec(Sharer sharer) {
			if (sharer.getSharerType()==Sharer.Type.PARAMETER) {
				Parameter source = (Parameter) sharer;
				switch(source.getOnlyParam()) {
				case "new":
				Persona persona = new Persona();
				// condivido l'oggetto
				personCmd.shareObject(persona);
				return "Instanza creata @";
				case "nome":
				Persona persona2 = personCmd.getSharedObject();
				persona2.setNome(nomeParam.getInputValue());
				return "Nome settato";
				case "cognome":
					Persona persona3 = personCmd.getSharedObject();
					persona3.setCognome(cognomeParam.getInputValue());
					return "Cognome settato";
				case "eta":
					Persona persona4 = personCmd.getSharedObject();
					persona4.setEtà(Integer.parseInt(etaParam.getInputValue()));
					return "Età settata";
				}
			}
			return null ;
		}
	};
	
	newParam.setExecution(execution);
	nomeParam.setExecution(execution);
	cognomeParam.setExecution(execution);
	etaParam.setExecution(execution);
	
	
	// aggiungo i comandi al terminale 
	
	terminal.addCommands(personCmd);
	
	terminal.open();
	
	
}
}
