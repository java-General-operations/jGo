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
import cloud.jgo.utils.command.terminal.phase.Rule;

public class GeneralTest {
	public static void main(String[] args) {

		// due bug da risolvere :
		// 1) welcome stampato all'infinito ?

		LocalCommand.color = Color.GREEN;
		Parameter.color = Color.YELLOW;
		DefaultPhase.color = Color.CYAN;

		// 1 passo : creo il terminale

		LocalPhaseTerminal terminal = new LocalPhaseTerminal();

		terminal.setName(£.colors("Person4j", Color.GREEN));

		terminal.useGeneralHelp();

		LocalCommand.setInputHelpExploitable(true);

		// 2 passo : creo le fasi

		Phase startPhase, personPhase, endPhase;

		startPhase = terminal.createPhase(1, "START", "Fase iniziale");
		personPhase = terminal.createPhase(2, "PERSON", "In questa fase si creano le persone");
		endPhase = terminal.createPhase(3, "END", "Fase finale - persona creata @");

		// 3 passo : mi creo i comandi della seconda fase

		LocalCommand create, set, to_string;

		create = new LocalCommand("create", "questo comando crea una persona");
		set = new LocalCommand("set", "Imposta");
		to_string = new LocalCommand("to_string", "gets Person string");

		create.setExecution(new Execution() {

			@Override
			public Object exec() {
				// TODO Auto-generated method stub
				Person person = new Person();
				create.shareObject(person);
				System.out.println("Persona Creata @");
				return null;
			}
		});

		// 4 passo : mi creo i parametri del comando : set

		Parameter name, surname, age;
		name = set.addParam("name", "Person name");
		surname = set.addParam("surname", "Person surname");
		age = set.addParam("age", "Person age");

		name.setInputValueExploitable(true);
		surname.setInputValueExploitable(true);
		age.setInputValueExploitable(true);

		name.setExecution(new Execution() {

			@Override
			public Object exec() {
				if (create.getSharedObject() != null) {
					if (name.getInputValue() != null) {
						((Person) create.getSharedObject()).setName(name.getInputValue());
						System.out.println("The person name is set @");
					}
				} else {
					System.err.println("Persona non creata #");
				}
				return null;
			}
		});

		surname.setExecution(new Execution() {

			@Override
			public Object exec() {
				if (create.getSharedObject() != null) {
					if (surname.getInputValue() != null) {
						((Person) create.getSharedObject()).setSurname(surname.getInputValue());
						System.out.println("The person surname is set @");
					}
				} else {
					System.err.println("Persona non creata #");
				}
				return null;
			}
		});

		age.setExecution(new Execution() {

			@Override
			public Object exec() {
				if (create.getSharedObject() != null) {
					if (age.getInputValue() != null) {
						((Person) create.getSharedObject()).setAge(Integer.parseInt(age.getInputValue()));
						System.out.println("The person age is set @");
					}
				} else {
					System.err.println("Persona non creata #");
				}
				return null;
			}
		});

		to_string.setExecution(new Execution() {

			@Override
			public Object exec() {

				if (create.getSharedObject() != null) {
					System.out.println(create.getSharedObject().toString());
				} else {
					System.err.println("Persona non creata #");
				}
				return null;
			}
		});
		
		
		//5 passo :ora devo impostare le regole 
		
		personPhase.satisfiableThrough(new Rule() {
			
			@Override
			public boolean verification() {
				if (create.getSharedObject()!=null) {
					if (((Person)create.getSharedObject()).getName()!=null&&((Person)create.getSharedObject()).getSurname()!=null&&((Person)create.getSharedObject()).getAge()>0) {
						return true ;
					}
					else {
						return false ;
					}
				}
				else {
					return false ;
				}
			}
			
			@Override
			public String ruleExplanation() {
				return "Devono essere impostati i campi della persona, per soddisfare|Accedere  questa fase !";
			}
		});
		
		
		
		//6 passO:imposto la regola di accesso all'ultima fase con la regola di soddisfazione della fase :Person
		
		endPhase.accessibleThrough(((DefaultPhase)personPhase).getSatisfiabilityRule());
		
		
		
		//7 passo:aggiungo i comandi alla seconda fase
		
		terminal.addCommandsToPhase(personPhase,create,set,to_string);
		
		
		// attivo il terminale 
		
		terminal.open();
	}
}
