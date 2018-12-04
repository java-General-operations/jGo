package cloud.jgo.test;

import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Parameter;
import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.terminal.LocalTerminal;

public class UnifyTest {
public static void main(String[] args) {
	
	// bene adesso
	// dobbiamo fare le seguenti cose 
	/**
	 * 1 verificare che non ci siano problemi con i parametri se c'è un comando con valore da input
	 * 2 verificare se il caso di mandare un mex di avviso quando non si da il valore input del comando, come faccio con i parametri
	 * 3 fare quello che ho fatto nel metodo executeInputCommand, quindi in tutti i metodi che eseguono i comandi e parametri
	 * 4 creare il metodo che unisce un comando con parametro, questo è il lavoro di stamattina.
	 */
	
	
	LocalTerminal terminal = new LocalTerminal();
	
	LocalCommand cd = new LocalCommand("cd","Changes the node");
	cd.setInputValueExploitable(true);
	cd.setExecution(new Execution() {
		
		@Override
		public Object exec() {
			
			System.out.println("Path:"+cd.getInputValue());
			cd.setInputValue(null);
			return null ;
		}
	});
	
	// quindi in questo caso, ho creato un comand con valore da input 
	
	terminal.addCommand(cd);
	terminal.open();
	
}
}
