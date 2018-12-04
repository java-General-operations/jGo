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
	
	// aggiungo qualche parametro
	
	Parameter cdRoot,cdBack = null ;
	
	cdRoot = cd.addParam("root","return to root node");
	cdBack = cd.addParam("back","return to previous node");
	Parameter isCurrent = cd.addParam("is:current","verifica");
	isCurrent.setInputValueExploitable(true);
	
	isCurrent.setExecution(new Execution() {
		
		@Override
		public Object exec() {
			// TODO Auto-generated method stub
			System.out.println(isCurrent.getInputValue()+" is current node :bu");
			return null ;
		}
	});
	
	cdRoot.setExecution(new Execution() {
		
		@Override
		public Object exec() {
			System.out.println("Sei ritornato nel nodo root");
			return null ;
		}
	});
	
	cdBack.setExecution(new Execution() {
		
		@Override
		public Object exec() {
			// TODO Auto-generated method stub
			System.out.println("Sei ritornato nel nodo precedente");
			return null ;
		}
	});
	
	terminal.addCommand(cd);
	
	// cerchiamo altri metodi in cui adattare la situazione del comando con valore da input
	
	terminal.open();
	
}
}
