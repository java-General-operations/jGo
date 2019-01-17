/**
 * JGO - A pure Java library,
 * its purpose is to make life easier for the programmer.
 *
 * J - Java
 * G - General
 * O - Operations
 *
 * URL Software : https://www.jgo.cloud/
 * URL Documentation : https://www.jgo.cloud/docs/
 *
 * Copyright © 2018 - Marco Martire (www.jgo.cloud)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License.
 *
 * You may obtain a copy of License at :
 * https://www.jgo.cloud/LICENSE.txt
 *
 * To collaborate on this project, you need to do it from the software site.
 * 
 */
package cloud.jgo.utils.command.terminal.phase;

import java.util.ArrayList;
import java.util.List;

import org.fusesource.jansi.Ansi.Color;

import cloud.jgo.j£;
import cloud.jgo.£;
import cloud.jgo.utils.ColorString;
import cloud.jgo.utils.command.Command;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Parameter;
import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.terminal.LocalTerminal;
import cloud.jgo.utils.command.terminal.TerminalColors;

// se si estende questa classe, vuol dire che si vuole ridefinire il metodo useResponseObjectData
// che ci permette di ottenere la risposta oggetto di ogni comando dato
// non si può definire il valore string che chiede i comandi
/**
 * 
 * @author Martire91<br>
 *         This class represents the phases terminal
 */
public class LocalPhaseTerminal extends LocalTerminal implements Structure {
	protected Phase currentPhase, startPhase = null;
	protected List<Phase> phases = new ArrayList<>();
	protected LocalCommand pointerCommand = null;
	protected LocalCommand resetCommand = null;
	protected LocalCommand describerCommand = null;
	// version 1.0.9 :
	// questo comando se :
	// - gli si da in pasto una fase, esegue dalla fase corrente a quella annunciata
	// - non gli si da niente, esegue soltanto la fase corrente
	// - poi ci saranno opportuni parametri che permetteranno di lanciare anche
	// dalla prima fase
	// a quella annunciata
	protected static LocalCommand phasesExecutorCommand = new LocalCommand("phase-execute",
			"This command executes a phase");
	protected static LocalCommand statusCommand = new LocalCommand("status", "View the report of the current phase");

	public static LocalCommand getStatusCommand() {
		return statusCommand;
	}

	/**
	 * This method returns the current phase
	 * 
	 * @return the current phase
	 */
	public Phase getCurrentPhase() {
		return this.currentPhase;
	}

	/**
	 * This method returns the start phase
	 * 
	 * @return the start phase
	 */
	public Phase getStartPhase() {
		return this.startPhase;
	}

	// version 1.0.9
	public void setPointerName(String pointerCommandName) {
		pointerCommand.setCommand(pointerCommandName);
	}

	@Override
	public String getCommandRequest() {
		String text = "£_:";
		if (currentPhase != null) {

			text = "£_(" + currentPhase.phaseName() + ") :";

		}
		return text;
	}

	/**
	 * This method creates a phase.<br>
	 * The negative numerical values are not possible
	 * 
	 * @param value
	 *            the phase value
	 * @param phaseName
	 *            the phase name
	 * @param phaseDescription
	 *            the phase description
	 * @param commands
	 *            the commands
	 * @return the phase
	 */
	public LocalPhase createPhase(final int value, String phaseName, String phaseDescription, Command... commands) {

		// verifico che non sia nessun phase con questo valore
		if (phase(value) == null && phase(phaseName) == null) {
			LocalPhase phase = new LocalPhase(phaseName, value, this);
			((LocalPhase) phase).setDescription(phaseDescription);

			if (commands != null) {
				((LocalPhase) phase).addCommands(commands);
				addCommands(commands);
			}
			if (value == 1) {
				startPhase = phase;
			}
			phases.add(phase);

			// qui costruisco il parametro per eseguire questa fase fino a ]

			Parameter phaseExecutionParam = phasesExecutorCommand.addParam(phase.phaseName(),
					"executes the " + £.escp(phase.phaseName()) + " phase");

			// se ci sono i comandi mi creo l'esecuzione del parametro che esegue la fase
			// altrimenti abbiamo solo il parametro aggiunto al comando, ma senza esecuzione
			phaseExecutionParam.setExecution(new Execution() {

				@Override
				public Object exec() {

					return phase.executesFromCurrentPhaseUpTo();

				}
			});

			// ]

			// qui solo se non è la fase start, perchè non ci serve avere un riferimento a
			// tale fase
			// eseguiamo il codice dell'aggiungimento link param. Tutto questo perchè
			// abbiamo il metodo reset
			// e il comando che si occupa di resettare alla prima fase la struttura
			if (value > 1) { // diamo per scontato che la prima fase è la numero 1
				// okok qui completo il metodo
				// aggiungo al puntatore un link che accederà a questa fase
				Parameter p_link = pointerCommand.addParam(phase.phaseName(),
						"Go to (" + phase.phaseName() + ") phase @");
				// senza valore da input

				// aggiungo l'esecuzione
				p_link.setExecution(new Execution() {

					@Override
					public Object exec() {

						// permetti l'accesso a questa fase appena impostata solo se

						changePhase(phase);

						return null;
					}
				});
			}
			// anche se la fase è la start mi serve attribuire una descrizione
			Parameter p_link_desc = describerCommand.addParam(phase.phaseName(),
					"This parameter describes the (" + phase.phaseName() + ") phase @");
			p_link_desc.setExecution(new Execution() {
				@Override
				public Object exec() {
					StringBuffer buffer = new StringBuffer();
					buffer.append(
							"==============================================================================================================================================================\n");
					buffer.append("Description of (" + phase.phaseName() + ")\n");
					buffer.append(
							"==============================================================================================================================================================\n");
					buffer.append(phase.description() + ".\n");
					List<Command> commands = phase.getCommands();
					if (commands.size() > 0) {
						buffer.append("# Supported commands :");
					}
					buffer.append(commands + "\n");
					buffer.append(
							"==============================================================================================================================================================\n");
					return buffer.toString();
				}
			});
			return phase;
		} else {
			return null;
		}
	}

	/**
	 * This method creates a phase.<br>
	 * The negative numerical values are not possible
	 * 
	 * @param value
	 *            the phase value
	 * @param phaseName
	 *            the phase name
	 * @param phaseDescription
	 *            the phase description
	 * @return the phase
	 */
	public LocalPhase createPhase(final int value, String phaseName, String phaseDescription) {
		// verifico che non sia nessun phase con questo valore
		if (phase(value) == null && phase(phaseName) == null) {
			LocalPhase phase = new LocalPhase(phaseName, value, this);

			((LocalPhase) phase).setDescription(phaseDescription);

			if (value == 1) {
				startPhase = phase;
			}
			phases.add(phase);

			// qui costruisco il parametro per eseguire questa fase fino a ]

			Parameter phaseExecutionParam = phasesExecutorCommand.addParam(phase.phaseName(),
					"executes the " + £.escp(phase.phaseName()) + " phase");

			// se ci sono i comandi mi creo l'esecuzione del parametro che esegue la fase
			// altrimenti abbiamo solo il parametro aggiunto al comando, ma senza esecuzione
			phaseExecutionParam.setExecution(new Execution() {

				@Override
				public Object exec() {

					// da definire ...

					return phase.executesFromCurrentPhaseUpTo();

				}
			});
			// ]

			// qui solo se non è la fase start, perchè non ci serve avere un riferimento a
			// tale fase
			// eseguiamo il codice dell'aggiungimento link param. Tutto questo perchè
			// abbiamo il metodo reset
			// e il comando che si occupa di resettare alla prima fase la struttura
			if (value > 1) { // diamo per scontato che la prima fase è la numero 1
				// okok qui completo il metodo
				// aggiungo al puntatore un link che accederà a questa fase
				Parameter p_link = pointerCommand.addParam(phase.phaseName(),
						"Go to (" + phase.phaseName() + ") phase @");
				// senza valore da input

				// aggiungo l'esecuzione
				p_link.setExecution(new Execution() {

					@Override
					public Object exec() {

						// permetti l'accesso a questa fase appena impostata solo se

						changePhase(phase);

						return null;
					}
				});
			}
			// anche se la fase è la start mi serve attribuire una descrizione
			Parameter p_link_desc = describerCommand.addParam(phase.phaseName(),
					"This parameter describes the (" + phase.phaseName() + ") phase @");
			p_link_desc.setExecution(new Execution() {
				@Override
				public Object exec() {
					StringBuffer buffer = new StringBuffer();
					buffer.append(
							"==============================================================================================================================================================\n");
					buffer.append("Description of (" + phase.phaseName() + ")\n");
					buffer.append(
							"==============================================================================================================================================================\n");
					buffer.append(phase.description() + ".\n");
					List<Command> commands = phase.getCommands();
					if (commands.size() > 0) {
						buffer.append("# Supported commands :");
					}
					buffer.append(commands + "\n");
					buffer.append(
							"==============================================================================================================================================================\n");
					return buffer.toString();
				}
			});
			return phase;
		} else {
			return null;
		}
	}

	/**
	 * This method adds commands into the phase
	 * 
	 * @param phase
	 *            the phase
	 * @param commands
	 *            the commands
	 */
	public void addCommandsToPhase(Phase phase, Command... commands) {
		if (commands != null) {
			((LocalPhase) phase).addCommands(commands);
			addCommands(commands);
			// adesso controllo se questa fase ha il corrispondente parametro
			// sul comando executor, altimenti lo aggiungo
			String phaseName = phase.phaseName();
			Parameter exist = null;
			for (Parameter param : phasesExecutorCommand.params()) {
				if (param.getOnlyParam().equals(phaseName)) {
					exist = param;
					break;
				}
			}
			if (exist == null) {
				// il parametro non esiste
				// allora lo creo
				Parameter p = phasesExecutorCommand.addParam(phase.phaseName(),
						"executes the " + £.escp(phase.phaseName()) + " phase");
				p.setExecution(new Execution() {

					@Override
					public Object exec() {

						return ((LocalPhase) phase).executesFromCurrentPhaseUpTo();
					}
				});
			}
		}
	}

	@Override
	public final void implOpen() {
		// do il comando da input

		String cmd = £._I();

		// prima cosa controllo se è uguale al comando di chiusura
		if (cmd.equals(getExitCommand())) {
			// chiudo la console
			close();
		}
		// ottengo il comando in tanto

		LocalCommand command = LocalCommand.getCommand(cmd, getCommands());
		ArrayList<Object> response = null;
		if (command != null) {

			// verifico se ha una fase
			if (command.hasAPhase()) {

				/*
				 * A una fase
				 */

				// recupero la fase del comando
				Phase phase = command.getBelongsTo();

				// adesso devo verificare se il comando si può eseguire
				// quindi se la fase attuale è uguale a quella del comando

				if (currentPhase.equals(phase)) {

					response = LocalCommand.executeInputCommand(cmd, phase.getCommands());
					if (response != null) {

						useCommandResponseData(response);
					}
				}
			} else {
				/*
				 * Non ha la fase qui eseguo il comando come se fosse nel localTerminal
				 */
				response = LocalCommand.executeInputCommand(cmd, getCommands());
				if (response != null) {

					useCommandResponseData(response);
				}
			}
		}
	}

	// il costruttore lo suo per inizializzare le fasi predefinite
	// in questo caso solo : pointerCommand
	protected LocalPhaseTerminal() {
	}

	public LocalPhaseTerminal(String terminalName) {
		// TODO Auto-generated constructor stub
		setName(terminalName);
		// imposto il comando status
		statusCommand.setExecution(new Execution() {
			@Override
			public Object exec() {
				if (currentPhase != null) {
					StringBuffer string = new StringBuffer();
					string.append("\n\t\t|Current Phase > ").append(currentPhase.phaseName().toUpperCase()).append("\n")
							.append("\t\t|Level = ").append(currentPhase.getValue() + "").append("\n")
							.append("\t\t|Accessible = ").append(currentPhase.isAccessible() + "").append("\n")
							.append("\t\t|Satisfied = ").append(currentPhase.isSatisfied() + "").append("\n")
							.append("\t\t|Supported commands = ").append(currentPhase.getCommands() + "");
					if ((currentPhase).getAccessibilityRule() != null) {
						string.append("\n\t\t|Access-Rule = ")
								.append((currentPhase).getAccessibilityRule().ruleExplanation());
					}
					if ((currentPhase).getSatisfiabilityRule() != null) {
						string.append("\n\t\t|Satisfaction-Rule = ")
								.append((currentPhase).getSatisfiabilityRule().ruleExplanation());
					}
					string.append("\n");
					return string.toString();
				} else
					return "NO current phase !!";
			}
		});
		// qui imposto l'esecuzione del comando che esegue le fasi
		phasesExecutorCommand.setInputValueExploitable(true);
		phasesExecutorCommand.setExecution(new Execution() {
			@Override
			public Object exec() {
				Object return_ = null;
				if (phasesExecutorCommand.getInputValue() != null) {
					// abbiamo una fase specificata da input
					String phaseName = phasesExecutorCommand.getInputValue();
					for (Phase phase : phases) {
						if (phase.phaseName().equals(phaseName)) {
							// okok abbiamo trovato la fase che vogliamo eseguire
							// quindi :
							return_ = ((LocalPhase) phase).executesFromCurrentPhaseUpTo();
							break;
						}
					}
				} else {
					// qui dobbiamo verificare se vi è una fase corrente
					if (currentPhase != null) {
						return_ = currentPhase.execute();
					}
				}
				return return_;
			}
		});
		this.pointerCommand = new LocalCommand("phase-goto", "This command points to a specific phase");
		this.describerCommand = new LocalCommand("describe", "This command describes a specific phase");
		this.describerCommand.setExecution(new Execution() {

			@Override
			public Object exec() {

				if (currentPhase != null) {

					StringBuffer buffer = new StringBuffer();
					buffer.append(
							"==============================================================================================================================================================\n");
					buffer.append("Description of (" + currentPhase.phaseName() + ")\n");
					buffer.append(
							"==============================================================================================================================================================\n");

					buffer.append(((LocalPhase) currentPhase).description() + ".\n\n");

					// qui inserisco i comandi supportati di questa fase

					List<Command> commands = ((LocalPhase) currentPhase).getCommands();
					if (commands.size() > 0) {
						buffer.append("# Supported commands :\n");
					}
					for (int i = 0; i < commands.size(); i++) {

						buffer.append(
								(i + 1) + ")" + commands.get(i).getCommand() + "=" + commands.get(i).getHelp() + "\n");
					}

					buffer.append(
							"==============================================================================================================================================================\n");

					return buffer.toString();
				} else {
					return null;
				}

			}
		});
		addCommands(this.pointerCommand, this.describerCommand, phasesExecutorCommand, statusCommand);
	}

	// questo metodo non va chiamato esplicitamente
	// e ne implementato : da semplicemente il valore
	// della fase start alla fase corrente Quindi la variabile
	// current phase. Quest'ultima viene modificata solo all'interno di questo
	// metodo
	// eppoi nella sottoclasse del terminale, ma il cambiamento del suo valore e a
	// noi impercettibile
	/**
	 * This method should not be called explicitly
	 */
	public void orients() {
		this.currentPhase = this.startPhase;
	}

	@Override
	public Phase nextPhase() {
		// quindi qui in tanto andiamo ad individuare il valore numerico della fase
		// corrente
		if (currentPhase.isSatisfied()) {
			int currentValuePhase = currentPhase.getValue();
			currentValuePhase = currentValuePhase + 1;
			// vado alla ricerca di una fase che abbia quel valore
			Phase found = null;
			for (int i = 0; i < phases.size(); i++) {
				int currentValue = phases.get(i).getValue();
				if (currentValue == currentValuePhase) {
					found = phases.get(i);
					break;
				}
			}
			if (found != null) {

				// qui verifico se la fase successiva è accessibile

				if (found.isAccessible()) {
					return currentPhase = found;
				} else {
					System.out.println(((LocalPhase) found).getAccessibilityRule().ruleExplanation());
					return null;
				}

			} else {
				return null;
			}
		} else {
			// non è soddisfatta la corrente fase
			System.out.println(((LocalPhase) currentPhase).getSatisfiabilityRule().ruleExplanation());
			return null;
		}
	}

	/**
	 * This method forces the transition to the previous phase
	 * 
	 * @return the previous phase
	 */
	public Phase forceToPreviousPhase() {
		int currentValuePhase = currentPhase.getValue();
		currentValuePhase = currentValuePhase - 1;
		// vado alla ricerca di una fase che abbia quel valore
		Phase found = null;
		for (int i = 0; i < phases.size(); i++) {
			int currentValue = phases.get(i).getValue();
			if (currentValue == currentValuePhase) {
				found = phases.get(i);
				break;
			}
		}
		if (found != null) {

			if (found.isAccessible()) {
				return currentPhase = found;
			} else {
				System.out.println(((LocalPhase) found).getAccessibilityRule().ruleExplanation());

				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public Phase previousPhase() {
		if (currentPhase.isSatisfied()) {
			// quindi qui in tanto andiamo ad individuare il valore numerico della fase
			// corrente
			int currentValuePhase = currentPhase.getValue();
			currentValuePhase = currentValuePhase - 1;
			// vado alla ricerca di una fase che abbia quel valore
			Phase found = null;
			for (int i = 0; i < phases.size(); i++) {
				int currentValue = phases.get(i).getValue();
				if (currentValue == currentValuePhase) {
					found = phases.get(i);
					break;
				}
			}
			if (found != null) {

				if (found.isAccessible()) {
					return currentPhase = (LocalPhase) found;
				} else {
					System.out.println(((LocalPhase) found).getAccessibilityRule().ruleExplanation());

					return null;
				}
			} else {
				return null;
			}
		} else {

			System.out.println(((LocalPhase) currentPhase).getSatisfiabilityRule().ruleExplanation());

			return null;
		}
	}

	@Override
	public LocalPhase changePhase(int phaseValue) {
		Phase phase = phase(phaseValue);
		if (phase != null) {
			if (!phase.equals(currentPhase)) {
				if (phase.isAccessible() && currentPhase.isSatisfied()) {
					currentPhase = (LocalPhase) phase;
				} else {
					if (((LocalPhase) phase).getAccessibilityRule() != null) {
						// scopriamo il perchè non è accessibile
						System.out.println(((LocalPhase) phase).getAccessibilityRule().ruleExplanation());
					}
					if (((LocalPhase) currentPhase).getSatisfiabilityRule() != null) {
						System.out.println(((LocalPhase) currentPhase).getSatisfiabilityRule().ruleExplanation());
					}
				}
			}
		}
		return (LocalPhase) phase;
	}

	@Override
	public LocalPhase changePhase(String phaseName) {
		Phase phase = phase(phaseName);
		if (phase != null) {
			if (!phase.equals(currentPhase)) {
				if (phase.isAccessible() && currentPhase.isSatisfied()) {
					currentPhase = (LocalPhase) phase;
				} else {
					if (((LocalPhase) phase).getAccessibilityRule() != null) {
						// scopriamo il perchè non è accessibile
						System.out.println(((LocalPhase) phase).getAccessibilityRule().ruleExplanation());
					}
					if (((LocalPhase) currentPhase).getSatisfiabilityRule() != null) {
						System.out.println(((LocalPhase) currentPhase).getSatisfiabilityRule().ruleExplanation());
					}
				}
			}
		}
		return (LocalPhase) phase;
	}

	@Override
	public void reset() {
		if (startPhase != null) {
			orients();
		} else {
			// si va alla ricerca di una fase primaria
			Phase p = phase(1);
			if (p != null) {
				currentPhase = (LocalPhase) p;
			}
		}
	}

	@Override
	public int countPhases() {

		return this.phases.size();
	}

	@Override
	public LocalPhase phase(String phaseName) {
		// vado a cercare
		// una fase con questo tipo
		Phase phase = null;
		for (int i = 0; i < phases.size(); i++) {
			if (phases.get(i).phaseName().equalsIgnoreCase(phaseName)) {
				phase = phases.get(i);
			}
		}
		return (LocalPhase) phase;
	}

	@Override
	public LocalPhase phase(int value) {
		Phase p = null;
		if (value > -1) {
			for (Phase phase : phases) {
				if (phase.getValue() == value) {
					p = phase;
					break;
				}
			}
		}
		return (LocalPhase) p;
	}

	@Override
	public Command resetCommand() {
		// TODO Auto-generated method stub
		return this.resetCommand;
	}

	@Override
	public void setResetCommand(String command) {
		this.resetCommand = new LocalCommand(command, "This command returns to the first phase");
		this.resetCommand.setExecution(new Execution() {

			@Override
			public Object exec() {

				reset();

				return null;
			}
		});
		// aggiungo il comando a comandi
		addCommand(this.resetCommand);
	}

	@Override
	public Phase changePhase(Phase phase) {

		// qui si deve permettere l'esecuzione di un metodo boolean
		// che può essere implementato

		if (phase != null) {
			if (!phase.equals(currentPhase)) {
				if (phase.isAccessible() && currentPhase.isSatisfied()) {
					currentPhase = (LocalPhase) phase;
				} else {
					if (((LocalPhase) phase).getAccessibilityRule() != null) {
						// scopriamo il perchè non è accessibile
						System.out.println(((LocalPhase) phase).getAccessibilityRule().ruleExplanation());
					}
					if (((LocalPhase) currentPhase).getSatisfiabilityRule() != null) {
						System.out.println(((LocalPhase) currentPhase).getSatisfiabilityRule().ruleExplanation());
					}
				}
			}
		}
		return currentPhase;
	}

	@Override
	public boolean removePhase(int value) {
		Phase phase = phase(value);

		if (phase != null) {

			// elimino tutti i comandi del terminale che hanno questa fase

			for (int i = 0; i < getCommands().size(); i++) {
				if (getCommands().get(i).hasAPhase()) {
					if (getCommands().get(i).getBelongsTo().equals(phase)) {
						getCommands().get(i).setBelongsTo(null);
					}
				}
			}

			// controllo se c'è un parametro associato a questa fase per il comando executor

			for (Parameter param : phasesExecutorCommand.params()) {
				if (param.getOnlyParam().equals(phase.phaseName())) {
					phasesExecutorCommand.removeParam(param.getOnlyParam());
					break;
				}
			}
			return phases.remove(phase);

		} else {
			return false;
		}
	}

	/**
	 * 
	 * @author Martire91<br>
	 *         This class represents a concrete phase
	 */
	static class LocalPhase implements Phase {

		/**
		 * this is a concrete product
		 */
		private static final long serialVersionUID = 12L;
		private Rule accessibilityRule = null;
		private Rule satisfiabilityRule = null;
		// version 1.0.9
		private When w = When.ALWAYS; // default value
		private String phaseName = null;
		private int value;
		private boolean accessible = true;
		// version 1.0.9
		private PhaseGroup group = null ;
		private boolean satisfied = true;
		private String description = null;
		private LocalPhaseTerminal t = null;
		// version 1.0.9
		private PhaseExecutionType[] types = { PhaseExecutionType.ALL_COMMANDS };

		@Override
		public Rule getSatisfiabilityRule() {
			return this.satisfiabilityRule;
		}

		@Override
		public Rule getAccessibilityRule() {
			return this.accessibilityRule;
		}

		public LocalPhase(String phaseName, int value, LocalPhaseTerminal t) {
			this.phaseName = phaseName;
			this.value = value;
			this.t = t;
			// nel momento in cui si crea una fase
			// ecco che gli viene attribuita una
			// esecuzione di default, diamo per scontato che
			// l'esecuzione è di tipo all commands e basta
			setExecution(null, this.types);
		}

		private List<Command> commands = new ArrayList<>();
		// version 1.0.9
		private Execution execution = null;

		/**
		 * This method adds a command into the phase
		 * 
		 * @param command
		 *            the command
		 */
		public void addCommand(Command command) {
			((LocalCommand) command).setBelongsTo(this);
			((LocalCommand) command).getHelpCommand().reload(((LocalCommand) command)); // aggiorno l'help del comando
			this.commands.add(command);
		}

		/**
		 * This method adds the commands into the phase
		 * 
		 * @param commands
		 *            the commands
		 */
		public void addCommands(Command... commands) {
			// TODO Auto-generated method stub
			for (int i = 0; i < commands.length; i++) {
				addCommand(commands[i]);
			}
		}

		@Override
		public boolean equals(Object obj) {
			LocalPhase phase = (LocalPhase) obj;
			if (getValue() == phase.getValue() && phaseName().equalsIgnoreCase(phase.phaseName())) {
				return true;

			} else {
				return false;
			}
		}

		@Override
		public String phaseName() {
			// TODO Auto-generated method stub
			return this.phaseName;
		}

		@Override
		public int getValue() {
			// TODO Auto-generated method stub
			return this.value;
		}

		@Override
		public int getCountCommands() {
			// TODO Auto-generated method stub
			return this.commands.size();
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.phaseName + "/" + this.value;
		}

		@Override
		public boolean isAccessible() {
			if (this.accessibilityRule != null) {
				return this.accessible = this.accessibilityRule.verification(); // qui chiamo il metodo
			} else {
				return this.accessible;
			}
		}

		@Override
		public void accessibleThrough(Rule rule) {
			this.accessibilityRule = rule;
		}

		@Override
		public List<Command> getCommands() {
			// TODO Auto-generated method stub
			return this.commands;
		}

		@Override
		public boolean isSatisfied() {
			if (this.satisfiabilityRule != null) {
				return this.satisfied = this.satisfiabilityRule.verification(); // qui chiamo il metodo
			} else {
				return this.satisfied;
			}
		}

		@Override
		public void satisfiableThrough(Rule rule) {
			// TODO Auto-generated method stub
			this.satisfiabilityRule = rule;
		}

		@Override
		public String description() {
			// TODO Auto-generated method stub
			return this.description;
		}

		/**
		 * This method sets the phase description
		 * 
		 * @param description
		 *            the description
		 */
		public void setDescription(String description) {
			this.description = description;
		}

		@Override
		public Object execute() {
			// okok qui devo verificare se a questa fase
			// corrisponde un parametro sul comando esecutore
			// in tal caso aggiorno l'esecuzione del parametro
			if (hasAnExecution()) {
				switch (w) {
				case ALWAYS:
					return this.execution.exec();
				case NEVER:
					return null;
				case IF_ACCESSIBLE:
					if (isAccessible())
						return this.execution.exec();
				case IF_SATISFIED:
					if (isSatisfied())
						return this.execution.exec();
				}
				return null;
			} else
				return null;
		}

		@Override
		public boolean hasAnExecution() {
			if (this.execution != null)
				return true;
			else
				return false;
		}

		public Execution getExecution() {
			return this.execution;
		}

		@Override
		public void validExecution(When when) {
			// TODO Auto-generated method stub
			this.w = when;
		}

		@Override
		public When getHypothesis() {
			// TODO Auto-generated method stub
			return this.w;
		}

		// metodo interno usato dal comando esecutore di fasi
		private Object executesAllPhasesUpToHere() {
			Object obj = null;
			int indexThis = getValue() - 1; // pos-array-level
			for (int i = 0; i < indexThis; i++) {
				obj = t.phases.get(i).execute();
				if (obj != null) {
					System.out.println(obj);
				}
			}
			return obj;
		}

		// Esegue dalla fase corrente a quella annunciata : PROVVISORIO ...
		// qui posso raccogliere i risultati in un arraylist.
		// Approfondire ...
		private Object executesFromCurrentPhaseUpTo() {
			Object result = null ;
			if (t.currentPhase == null)
				return null;
			int currentPhaseIndex = t.currentPhase.getValue() - 1;
			int thisPhaseIndex = getValue() - 1;
			if (currentPhaseIndex < 0 || thisPhaseIndex < 0)
				return null;
			PhaseGroup group = null ;
			for (int i = currentPhaseIndex; i <= thisPhaseIndex; i++) {
				Phase ph = t.phases.get(i);
				if(group!=null) {
					if (ph.membershipGroup()==null)break;/* qui esco, poichè c'è un gruppo, però si è riscontrato un elemento che non ha proprio il gruppo*/
					if (!ph.membershipGroup().equals(group))break;/* anche qui esco, in quanto il gruppo dell'elemento non compacia con quello registrato in group*/
				}
				if (i == currentPhaseIndex)group = ph.membershipGroup();	
				// se gruppo non ha un valore, eseguo la fase ed esco
				if(group==null) {result = ph.execute();break;}
				else result = ph.execute(); 
			}
			return result;
		}

		@Override
		public LocalPhase setExecution(Execution execution, PhaseExecutionType... types) {
			if (types.length == 0)
				return null;
			for (PhaseExecutionType phaseExecutionType : types) {
				switch (phaseExecutionType) {
				case ALL_COMMANDS:
					// coinvolgo i comandi :
					this.execution = new Execution() {
						// PROVVISORIA @
						@Override
						public Object exec() {
							Object obj = null;
							for (Command command : getCommands()) {
								obj = command.execute();
								if (obj != null) {
									System.out.println(obj);
								}
							}
							return null;
						}
					};
					break;
				case ALL_COMMANDS_AND_PARAMETERS:
					// coinvolgo comandi e parametri :
					this.execution = new Execution() {

						@Override
						public Object exec() {
							Object obj, obj2;
							obj = null;
							obj2 = null;
							for (Command command : getCommands()) {
								obj = command.execute();
								if (obj != null) {
									System.out.println(obj);
								}
								// params execution :
								for (Parameter param : command.params()) {
									obj2 = param.execute();
									if (obj2 != null) {
										System.out.println(obj2);
									}
								}
							}
							return null;
						}
					};
					break;
				case CUSTOM:
					if (execution == null)
						return null;
					else
						this.execution = execution;
					break;
				default:
					break;
				}
			}
			this.types = types;
			return this;
		}

		@Override
		public PhaseExecutionType[] getExecutionTypes() {
			// TODO Auto-generated method stub
			return this.types;
		}

		@Override
		public PhaseGroup membershipGroup() {
			// TODO Auto-generated method stub
			return this.group ;
		}
		
		public void setMembershipGroup(PhaseGroup group){
			this.group = group ;
		}
	}
}
