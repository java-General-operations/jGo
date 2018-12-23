package test;

import java.util.List;

import cloud.jgo.j£;
import cloud.jgo.£;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Parameter;
import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.execution.SharedExecution;
import cloud.jgo.utils.command.terminal.LocalTerminal;

public class LocalTerminalTest {
@SuppressWarnings("static-access")
public static void main(String[] args) {
	
	
	// okok adesso dobbiamo testare se da problemi con i valori da input
	
	
	
	LocalTerminal terminal = new LocalTerminal();
	
	terminal.useGeneralHelp();
	
	LocalCommand.setInputHelpExploitable(true);
	
	
	// mi creo qualche comando 
	
	LocalCommand p,p2 ;
	LocalCommand start = new LocalCommand("start","start");
	start.setExecution(new Execution() {
		
		@Override
		public Object exec() {
			// TODO Auto-generated method stub
			return £.openTerminal();
		}
	});
	p = new LocalCommand("p","p");
	p2 = new LocalCommand("p2","p2");
	
	p.setInputValueExploitable(true);
	p2.setInputValueExploitable(true);
	
	
	
	SharedExecution execution = new MySharedExecution();
	

	p.setExecution(execution);p2.setExecution(execution);
	
	
	// aggiungo i comandi al terminale 
	
	terminal.addCommands(p,p2,start);
	
	
	terminal.open();
	
	
}
}
