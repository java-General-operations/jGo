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
import cloud.jgo.utils.command.Command;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Parameter;
import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.terminal.LocalTerminal;

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
	// version 1.0.9 : esegue solo le esecuzioni dei comandi, ma non dei parametri
	public static LocalCommand phasesExecutorCommand = new LocalCommand("phases-executor",
			"This command executes a phase");

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
		return startPhase;
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
	public Phase createPhase(final int value, String phaseName, String phaseDescription, Command... commands) {

		// verifico che non sia nessun phase con questo valore
		if (phase(value) == null && phase(phaseName) == null) {
			Phase phase = PhasesFactory.create(phaseName, value);

			((DefaultPhase) phase).setDescription(phaseDescription);

			if (commands != null) {
				((DefaultPhase) phase).addCommands(commands);
			}

			// aggiungo i comandi alla lista di comandi
			// questa lo commentata perchè è più giusto che i comandi che si aggiungono ad
			// una fase non si aggiungano anche ai comandi del terminale
			if (commands != null) {
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

			if (commands != null) {
				phaseExecutionParam.setExecution(new Execution() {

					@Override
					public Object exec() {

						List<Command> phaseCommands = phase.getCommands();

						for (Command command : phaseCommands) {

							System.out.println(command.execute());

						}
						return null;
					}
				});
			}

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
			((DefaultPhase) phase).addCommands(commands);
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
			if (exist != null) {
				// aggiorno l'esecuzione dei comandi aggiunti
				exist.setExecution(new Execution() {

					@Override
					public Object exec() {
						List<Command> phaseCommands = phase.getCommands();

						for (Command command : phaseCommands) {

							System.out.println(command.execute());

						}
						return null;
					}
				});
			} else {
				// vediamo piano piano se ci arriva qui ...
				System.out.println("C'è arrivato ###");
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
		// qui imposto l'esecuzione del comando che esegue le fasi 
				phasesExecutorCommand.setInputValueExploitable(true);
				phasesExecutorCommand.setExecution(new Execution() {
					
					@Override
					public Object exec() {
						if (phasesExecutorCommand.getInputValue()!=null) {
							boolean executed = false ;
							for(Phase current:phases) {
								
								if (phasesExecutorCommand.getInputValue().equals(current.phaseName())) {
									
									List<Command> phaseCommands = current.getCommands();

									for (Command command : phaseCommands) {

										System.out.println(command.execute());

									}
								}
								
							}
						}
						else {
							// da definire ...
						}
						return null ;
					}
				});
		this.pointerCommand = new LocalCommand("use", "This command points to a specific phase");
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

					buffer.append(((DefaultPhase) currentPhase).description() + ".\n");

					// qui inserisco i comandi supportati di questa fase

					List<Command> commands = ((DefaultPhase) currentPhase).getCommands();
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
		// aggiungo il comando
		addCommand(this.pointerCommand);
		addCommand(this.describerCommand);
		// aggiungo il comando statico che esegue le fasi
		addCommand(phasesExecutorCommand);
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
					System.out.println(((DefaultPhase) found).getAccessibilityRule().ruleExplanation());
					return null;
				}

			} else {
				return null;
			}
		} else {
			// non è soddisfatta la corrente fase
			System.out.println(((DefaultPhase) currentPhase).getSatisfiabilityRule().ruleExplanation());
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
				System.out.println(((DefaultPhase) found).getAccessibilityRule().ruleExplanation());

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
					return currentPhase = found;
				} else {
					System.out.println(((DefaultPhase) found).getAccessibilityRule().ruleExplanation());

					return null;
				}
			} else {
				return null;
			}
		} else {

			System.out.println(((DefaultPhase) currentPhase).getSatisfiabilityRule().ruleExplanation());

			return null;
		}
	}

	@Override
	public Phase changePhase(int phaseValue) {
		Phase phase = phase(phaseValue);
		if (phase != null) {
			if (!phase.equals(currentPhase)) {
				if (phase.isAccessible() && currentPhase.isSatisfied()) {
					currentPhase = phase;
				} else {
					if (((DefaultPhase) phase).getAccessibilityRule() != null) {
						// scopriamo il perchè non è accessibile
						System.out.println(((DefaultPhase) phase).getAccessibilityRule().ruleExplanation());
					}
					if (((DefaultPhase) currentPhase).getSatisfiabilityRule() != null) {
						System.out.println(((DefaultPhase) currentPhase).getSatisfiabilityRule().ruleExplanation());
					}
				}
			}
		}
		return phase;
	}

	@Override
	public Phase changePhase(String phaseName) {
		Phase phase = phase(phaseName);
		if (phase != null) {
			if (!phase.equals(currentPhase)) {
				if (phase.isAccessible() && currentPhase.isSatisfied()) {
					currentPhase = phase;
				} else {
					if (((DefaultPhase) phase).getAccessibilityRule() != null) {
						// scopriamo il perchè non è accessibile
						System.out.println(((DefaultPhase) phase).getAccessibilityRule().ruleExplanation());
					}
					if (((DefaultPhase) currentPhase).getSatisfiabilityRule() != null) {
						System.out.println(((DefaultPhase) currentPhase).getSatisfiabilityRule().ruleExplanation());
					}
				}
			}
		}
		return phase;
	}

	@Override
	public void reset() {
		if (startPhase != null) {
			orients();
		} else {
			// si va alla ricerca di una fase primaria
			Phase p = phase(1);
			if (p != null) {
				currentPhase = p;
			}
		}
	}

	@Override
	public int countPhases() {

		return this.phases.size();
	}

	@Override
	public Phase phase(String phaseName) {
		// vado a cercare
		// una fase con questo tipo
		Phase phase = null;
		for (int i = 0; i < phases.size(); i++) {
			if (phases.get(i).phaseName().equalsIgnoreCase(phaseName)) {
				phase = phases.get(i);
			}
		}
		return phase;
	}

	@Override
	public Phase phase(int value) {
		Phase p = null;
		if (value > -1) {
			for (Phase phase : phases) {
				if (phase.getValue() == value) {
					p = phase;
					break;
				}
			}
		}
		return p;
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
					currentPhase = phase;
				} else {
					if (((DefaultPhase) phase).getAccessibilityRule() != null) {
						// scopriamo il perchè non è accessibile
						System.out.println(((DefaultPhase) phase).getAccessibilityRule().ruleExplanation());
					}
					if (((DefaultPhase) currentPhase).getSatisfiabilityRule() != null) {
						System.out.println(((DefaultPhase) currentPhase).getSatisfiabilityRule().ruleExplanation());
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
}
