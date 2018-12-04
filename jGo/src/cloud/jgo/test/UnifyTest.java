package cloud.jgo.test;

import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Parameter;
import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.terminal.LocalTerminal;

public class UnifyTest {
public static void main(String[] args) {
	//okok testerò meglio queste classi
	// nello sviluppo di node4j, e cercherò anche
	// di usare le fasi per rintracciare eventuali bugs
	LocalTerminal terminal = new LocalTerminal();
	
	LocalCommand cd = new LocalCommand("cd","Changes the node");
	
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
	// mi creo un altro parametro 
	
	Parameter cdParam = cd.addParam("cd","changes the node");
	cdParam.setInputValueExploitable(true);
	cdParam.setExecution(new Execution() {
		
		@Override
		public Object exec() {
			System.out.println(cdParam.getInputValue());
			return null ;
		}
	});
	
	terminal.addCommand(cd);
	
	// cerchiamo altri metodi in cui adattare la situazione del comando con valore da input

	terminal.open();
}
}
