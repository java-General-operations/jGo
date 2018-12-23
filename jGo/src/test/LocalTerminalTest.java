package test;

import java.util.List;

import cloud.jgo.j£;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Parameter;
import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.execution.SharedExecution;
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
	
	
	SharedExecution execution = new SharedExecution() {
		
		@Override
		public Object exec() {
			// TODO Auto-generated method stub
			return null;
		}
	};
	
	
	
	
	
	
}
}
