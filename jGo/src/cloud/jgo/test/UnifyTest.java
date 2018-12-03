package cloud.jgo.test;

import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Parameter;
import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.terminal.LocalTerminal;

public class UnifyTest {
public static void main(String[] args) {
	
	// okok aggiungiamo questa nuova possibilità 
	// per i comandi, può capitare che si vuole
	// associare un parametro a un comando, in 
	// tal modo, quando si inserisce il comando
	// in realtà si sta inserendo il comando + il parametro
	// ...
	
	LocalTerminal terminal = new LocalTerminal();
	
	LocalCommand cd = new LocalCommand("cd","This command changes the node");
	
	
	Parameter cdParameter = cd.addParam("cd","changes the node");
	cdParameter.setInputValueExploitable(true);
	
	cdParameter.setExecution(new Execution() {
		
		@Override
		public Object exec() {
			
			
			String inputValue = cdParameter.getInputValue();
			
			System.out.println("Ecco :"+inputValue);
			
			return null ;
		}
	});
	
	
	cd.merge(cdParameter);
	
	terminal.addCommand(cd);
	
	terminal.open();
	
	
	
	
}
}
