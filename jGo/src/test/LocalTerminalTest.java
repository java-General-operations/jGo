package test;

import java.util.List;

import cloud.jgo.j£;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Parameter;
import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.terminal.LocalTerminal;

public class LocalTerminalTest {
@SuppressWarnings("static-access")
public static void main(String[] args) {
	
	// un altra cosa che potrei fare è la seguente
	// crearmi un altro metodo che praticamente condivide
	// pienamente il parametro, e per questi tipi di parametri
	// deve esserci solo una esecuzione che li gestisce
	// tutti, facilitando la vita del programmatore, quindi
	// quindi risparmiandogli di creare per ogni parametro
	// condiviso, una specifica esecuzione
	
	
	// Okok Ora devo assicurarmi che il discorso del parametro
	// condiviso funzioni anche tra + comandi, e non solo due
	
	
	
	LocalTerminal terminal = new LocalTerminal();
	
	terminal.useGeneralHelp();
	
	LocalCommand.setInputHelpExploitable(true);
	
	
	// mi creo qualche comando 
	
	LocalCommand p,p2 ;
	p = new LocalCommand("p","p");
	p2 = new LocalCommand("p2","p2");
	
	
	// parameters p:
	Parameter nodeValue,nodeName ;
	
	nodeValue = p.addParam("nodeValue","nodeValue");
	nodeName = p.addParam("nodeName","nodeName");
	
	nodeName.setExecution(new Execution() {
		
		@Override
		public Object exec() {
			
			String input = nodeName.getInputValue();
			
			System.out.println("Come nodeName di p:"+input);
			
			return null ;
			
		}
	});
	
	nodeValue.setExecution(new Execution() {
		
		@Override
		public Object exec() {
			
			String input = nodeValue.getInputValue();
			
			System.out.println("Come nodeValue di p:"+input);
			
			return null ;
		}
	});
	
	nodeValue.setInputValueExploitable(true);
	nodeName.setInputValueExploitable(true);
	
	
	
	// condivido i due parametri con il comando p2
	
	Parameter nodeValue2=p2.shareParameter(nodeValue);
	Parameter nodeName2=p2.shareParameter(nodeName);
	Parameter param = p2.addParam("test", "test");
	
	
	nodeName2.setExecution(new Execution() {
		
		@Override
		public Object exec() {
			
			String input = nodeName2.getInputValue();
			
			System.out.println("Come nodeName di p2:"+input);
			
			return null ;
		}
	});
	
	nodeValue2.setExecution(new Execution() {
		@Override
		public Object exec() {
			
			String input = nodeValue2.getInputValue();
			
			System.out.println("Come nodeValue di p2:"+input);
			
			return null ;
		}
	});
	
	// mi creo l'ultimo comando 
	
	LocalCommand p3 = new LocalCommand("p3", "p3");
	Parameter nodeName3 = p3.shareParameter(nodeName);
	Parameter nodeValue3 = p3.shareParameter(nodeValue);
	
	
	
	nodeName3.setExecution(new Execution() {
		
		@Override
		public Object exec() {
			// TODO Auto-generated method stub
			System.out.println(nodeName3.getInputValue());
			return null ;
		}
	});
	
	nodeValue3.setExecution(new Execution() {
		
		@Override
		public Object exec() {
			// TODO Auto-generated method stub
			System.out.println(nodeValue3.getInputValue());
			return null ;
		}
	});
	
	terminal.addCommands(p,p2,p3);
	
	
	

	terminal.open();
	
}
}
