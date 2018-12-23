package test;

import java.util.List;

import cloud.jgo.j£;
import cloud.jgo.£;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Parameter;
import cloud.jgo.utils.command.Sharer;
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
	
	
	
	Execution nodeNameExecution = new SharedExecution() {
		
		@Override
		protected Object sharedExec(Sharer sharer) {
			
			
			
			Parameter p = (Parameter) sharer ;
			
			
			System.out.println("NodeName:"+p.getInputValue()+" - parent command:"+p.getParent().getCommand());
			
			
			return null ;
			
		}
	};
	
	
	
	Execution nodeValueExecution = new SharedExecution() {
		
		@Override
		protected Object sharedExec(Sharer sharer) {
			
			
			
			Parameter p = (Parameter) sharer ;
			
			
			System.out.println("NodeValue:"+p.getInputValue()+" - parent command:"+p.getParent().getCommand());
			
			
			return null ;
			
		}
	};
	
	// in tanto installo le due esecuzioni nei due parametri originali
	
	nodeName.setExecution(nodeNameExecution);
	nodeValue.setExecution(nodeValueExecution);
	
	
	// ora condivido i due parametri 
	
	// con create
	
	create.shareItEntirely(nodeName);
	create.shareItEntirely(nodeValue);
	
	// aggiungo i comandi al terminale 
	
	terminal.addCommands(create,set);
	
	terminal.open();
	
	
	
	
	
}
}
