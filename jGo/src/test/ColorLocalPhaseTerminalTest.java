package test;

import java.util.ArrayList;
import java.util.List;

import org.fusesource.jansi.Ansi.Color;

import cloud.jgo.jjdom.dom.nodes.xml.XMLDocument;
import cloud.jgo.jjdom.dom.nodes.xml.XMLElement;
import cloud.jgo.utils.command.Command;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.color.ColorLocalCommand;
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
				
				// mi creo la prima fase 
				
				Phase phase = t.createPhase(1, "connection", "connection",new Command[] {});
				
				
		
	}
}
