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
	
	
	// okok siamo giunti all'ultimo obbiettivo :
	// condividere pienamente un parametro
	
	LocalTerminal terminal = new LocalTerminal();
	
	terminal.useGeneralHelp();
	
	LocalCommand.setInputHelpExploitable(true);
	
	
	// mi creo qualche comando 
	
	LocalCommand set,create ;
	
	set = new LocalCommand("set","set");
	create = new LocalCommand("create","create");
	
	// mi creo i parametri di set per dire 
	
	Parameter nodeName,nodeValue ;
	
	nodeName = set.addParam("nodeName","nodeName");
	nodeValue = set.addParam("nodeValue", "nodeValue");
	
	nodeName.setInputValueExploitable(true);
	
	nodeValue.setInputValueExploitable(true);
	
	
	// un parametro per essere condiviso interamente, deve avere una esecuzione condividibile
	
	
	Execution ex = new MySharedExecution();
	
	
	// per prima cosa setto l'esecuzione dei parametri
	
	nodeName.setExecution(ex);
	nodeValue.setExecution(ex);
	
	
	
	// okok posso condividere i parametri con create 
	
	create.shareItEntirely(nodeName,(SharedExecution) ex);
	create.shareItEntirely(nodeValue,(SharedExecution) ex);
	
	
	
	// quindi adesso se voglio eliminare un parametro condiviso
	// uso il metodo per eliminare parametri normale 
	
	boolean deleted=create.removeParam("nodeName");
	boolean deleted2 = create.removeParam("nodeValue");
	
	System.out.println("Parametro eliminato:"+deleted);
	System.out.println("Parametro eliminato:"+deleted2);
	
	System.out.println("Has parameters:"+create.hasParameters());
	
	
	
	
}
}
