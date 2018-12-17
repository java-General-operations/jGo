package test;

import org.fusesource.jansi.Ansi.Color;

import cloud.jgo.£;
import cloud.jgo.utils.ColorString;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Parameter;
import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.terminal.LocalTerminal;
import cloud.jgo.utils.command.terminal.phase.DefaultPhase;
import cloud.jgo.utils.command.terminal.phase.LocalPhaseTerminal;
import cloud.jgo.utils.command.terminal.phase.Phase;
import cloud.jgo.utils.command.terminal.phase.PhasesFactory;

public class GeneralTest {
	public static void main(String[] args) {
		
		// due bug da risolvere :
		// 1) welcome stampato all'infinito ?
		// 2) Si deve poter scegliere il nome del comando puntatore
		// 3) se diamo il comando describe su una fase, la descrive. Tuttavia dobbiamo correggere la cornicetta.
		// 4) risolvere il bug del metodo welcome()

		LocalCommand.color = Color.GREEN;
		Parameter.color = Color.YELLOW;
		DefaultPhase.color = Color.CYAN;

		LocalCommand.setInputHelpExploitable(true);

		LocalPhaseTerminal terminal = new LocalPhaseTerminal();
		
		terminal.useGeneralHelp();

		terminal.setName("DomT4j");

		// creo le semplici fasi

		Phase startPhase = terminal.createPhase(1, "START", "Fase iniziale @");
		Phase compilePhase = terminal.createPhase(2, "compile", "In questa fase si compilano i sorgenti");
		compilePhase.setWelcome("Welcome");
		Phase deployPhase = terminal.createPhase(3, "deploy", "In questa fase si deploya l'applicativo");

		// comandi 2 fase :

		LocalCommand srcCommand = new LocalCommand("src", "indica la directory");
		LocalCommand comp = new LocalCommand("comp", "compile");
		LocalCommand properties = new LocalCommand("props","the system properties");
		Parameter osName,osVersion = null ;
		osName = properties.addParam("os.name", "os.name");
		osVersion = properties.addParam("os.version","os.version");
		osName.setExecution(new Execution() {
			
			@Override
			public Object exec() {
				// TODO Auto-generated method stub
				return System.getProperty("os.name");
			}
		});
		
		osVersion.setExecution(new Execution() {
			
			@Override
			public Object exec() {
				// TODO Auto-generated method stub
				return System.getProperty("os.version");
			}
		});

		srcCommand.setInputValueExploitable(true);
		comp.setExecution(new Execution() {
			
			@Override
			public Object exec() {
				System.out.println("Applicazione compilata @");
				terminal.changePhase(terminal.nextPhase());
				return null ;
			}
		});
		
		srcCommand.setExecution(new Execution() {
			
			@Override
			public Object exec() {
				System.out.println("src-dir is set ="+srcCommand.getInputValue());
				return null ;
			}
		});
		
		// aggiungo i comandi alla fase apposita 
		
		terminal.addCommandsToPhase(compilePhase,srcCommand,comp,properties);
		
		
		terminal.open();

	}
}
