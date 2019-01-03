package test;

import cloud.jgo.utils.command.Command;
import cloud.jgo.utils.command.terminal.phase.ColorLocalPhaseTerminal;
import cloud.jgo.utils.command.terminal.phase.DefaultPhase;
import cloud.jgo.utils.command.terminal.phase.LocalPhaseTerminal;
import cloud.jgo.utils.command.terminal.phase.Phase;
import cloud.jgo.utils.command.terminal.phase.Rule;

public class ColorLocalPhasesTerminalTest {
public static void main(String[] args) {
	
	
	ColorLocalPhaseTerminal terminal = new ColorLocalPhaseTerminal();
	terminal.useGeneralHelp();
	
	
	// 1 fase : start 
	
	Phase start = terminal.createPhase(1, "start", "start", new Command[] {});
	Phase seconda = terminal.createPhase(2, "seconda", "seconda", new Command[] {});
		
	
	
	terminal.open();
	
	
}
}
