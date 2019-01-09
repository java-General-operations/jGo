package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.fusesource.jansi.Ansi.Color;

import cloud.jgo.jjdom.dom.nodes.xml.XMLDocument;
import cloud.jgo.jjdom.dom.nodes.xml.XMLElement;
import cloud.jgo.utils.command.Command;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Sharer;
import cloud.jgo.utils.command.color.ColorLocalCommand;
import cloud.jgo.utils.command.execution.SharedExecution;
import cloud.jgo.utils.command.terminal.LocalTerminal;
import cloud.jgo.utils.command.terminal.TerminalColors;
import cloud.jgo.utils.command.terminal.phase.ColorLocalPhaseTerminal;
import cloud.jgo.utils.command.terminal.phase.LocalPhaseTerminal;
import cloud.jgo.utils.command.terminal.phase.Phase;

public class ColorLocalPhaseTerminalTest {
	public static void main(String[] args) {
		

				
//				TerminalColors.PARAMETER_COLOR = Color.YELLOW;
//				TerminalColors.COMMAND_COLOR = Color.CYAN;
//				TerminalColors.PHASE_COLOR = Color.MAGENTA;
				
				

				LocalPhaseTerminal t = new LocalPhaseTerminal("mio terminal");
				
				t.useGeneralHelp();
				
				LocalCommand.setInputHelpExploitable(true);
				
				// mi creo la prima fase 
				
				Phase phase = t.createPhase(1, "connection", "connection",null);
				
				// mi creo i comandi di questa fase
				
				LocalCommand connect,migrate,download,update ;
				
				connect = new LocalCommand("connect", "connect");
				migrate = new LocalCommand("migrate", "migrate");
				download = new LocalCommand("download", "download");
				update = new LocalCommand("update", "update");
				
				SharedExecution execution = new SharedExecution() {
					
					@Override
					protected Object sharedExec(Sharer sharer) {
						
						if (sharer.getSharerType()==Sharer.Type.COMMAND) {
							
							Command source = (Command) sharer;
							
							return source.getCommand()+" successfully (OK)";							
						}
						else return null ;
					}
				};
				
				connect.setExecution(execution);
				migrate.setExecution(execution);
				download.setExecution(execution);
				update.setExecution(execution);
				
				// okok aggiungo i comandi alla fase
				
				t.addCommandsToPhase(phase, connect,migrate,download,update);
				
				String toString = Arrays.toString(LocalPhaseTerminal.phasesExecutorCommand.params());
				System.out.println(toString);
				
				// elimino la fase
				
				boolean result = t.removePhase(1);
				
				System.out.println(result);
				System.out.println(Arrays.toString(LocalPhaseTerminal.phasesExecutorCommand.params()));
				
				t.createPhase(1, "ciao", "ciao",null);
				
				System.out.println(Arrays.toString(LocalPhaseTerminal.phasesExecutorCommand.params()));
				
				
				
	}
}
