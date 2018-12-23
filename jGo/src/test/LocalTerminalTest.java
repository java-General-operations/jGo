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
	p = new LocalCommand("p","p");
	p2 = new LocalCommand("p2","p2");
	
	
	Parameter paramP,paramP2 ;
	
	paramP = p.addParam("test_p","test p");
	
	paramP2 = p2.addParam("test_p2","test p2");
	
	
	// adesso mi creo una esecuzione che dovrà essere condivisa tra i params di p e p2
	
	Execution execution = new MySharedExecution();
	
	paramP.setExecution(execution);
	paramP2.setExecution(execution);
	
	
	
	// aggiungo i comandi 
	
	terminal.addCommands(p,p2);
	
	terminal.open();
	
	
	
}
}
