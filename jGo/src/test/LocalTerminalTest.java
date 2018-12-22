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
	
	// prossimo passo : filtrare i parametri condivisi di un comando
	
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
	
	terminal.addCommands(p,p2);
	

	// cerco di ottenere i parametri del secondo comando: solo quelli condivisi
	
	List<Parameter> sharedParams = p2.getUnSharedParameters();
	
	for (Parameter parameter : sharedParams) {
		System.out.println(parameter);
	}
	
}
}
