package test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.util.Map.Entry;

import org.fusesource.jansi.Ansi.Color;

import cloud.jgo.£;
import cloud.jgo.net.Server;
import cloud.jgo.net.ServerTypes;
import cloud.jgo.net.factorys.ServersFactory;
import cloud.jgo.net.tcp.TCPServerTypes;
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

		LocalCommand.color = Color.GREEN;
		Parameter.color = Color.YELLOW;
		DefaultPhase.color = Color.CYAN;


		// 1 passo : creo il terminale 
		
		LocalPhaseTerminal terminal = new LocalPhaseTerminal();
		
		terminal.setName(£.colors("Person4j",Color.GREEN));
		
		terminal.useGeneralHelp();
		
		LocalCommand.setInputHelpExploitable(true);
		
		// 2 passo : creo le fasi 
		
		Phase startPhase,personPhase,endPhase ;
		
		startPhase = terminal.createPhase(1, "START", "Fase iniziale");
		personPhase = terminal.createPhase(2, "PERSON", "In questa fase si creano le persone");
		endPhase = terminal.createPhase(3, "END", "Fase finale - persona creata @");
		
		// 3 passo : mi creo i comandi della seconda fase
		
	
		
		
	}
}
