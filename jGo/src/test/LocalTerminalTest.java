package test;

import org.fusesource.jansi.Ansi.Color;

import cloud.jgo.£;
import cloud.jgo.utils.ColorString;
import cloud.jgo.utils.command.CommandsStore;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Parameter;
import cloud.jgo.utils.command.terminal.LocalTerminal;
import cloud.jgo.utils.command.terminal.phase.DefaultPhase;
import cloud.jgo.utils.command.terminal.phase.Phase;

public class LocalTerminalTest {
public static void main(String[] args) {
	
	LocalCommand.color = Color.CYAN;
	Parameter.color = Color.YELLOW;
	
	
	LocalTerminal terminal = new LocalTerminal();
	
	terminal.setName("DomT4j_"+new ColorString().append("(",Color.GREEN).append("@",Color.MAGENTA).append(")",Color.GREEN).toString());
	
	terminal.useGeneralHelp();
	
	terminal.addCommands(CommandsStore.OPEN_COMMAND,CommandsStore.ALERT_COMMAND,CommandsStore.FILE_COMMAND);
	
	terminal.open();
	
	
	
}
}
