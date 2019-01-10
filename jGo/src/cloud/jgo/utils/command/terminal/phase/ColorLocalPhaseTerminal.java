package cloud.jgo.utils.command.terminal.phase;

import static org.fusesource.jansi.Ansi.ansi;

import java.util.List;

import org.fusesource.jansi.AnsiConsole;
import org.fusesource.jansi.Ansi.Color;

import cloud.jgo.j£;
import cloud.jgo.£;
import cloud.jgo.utils.ColorString;
import cloud.jgo.utils.command.Command;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Parameter;
import cloud.jgo.utils.command.color.ColorLocalCommand;
import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.terminal.LocalTerminal;
import cloud.jgo.utils.command.terminal.TerminalColors;

public class ColorLocalPhaseTerminal extends LocalPhaseTerminal {

	// version 1.0.9
	/**
	 * Simple error message
	 * 
	 * @param msg
	 *            the message
	 * @return error message
	 */
	public static String error(String msg) {
		return ansi().fg(Color.RED).a(msg + " #").reset().toString();
	}

	// version 1.0.9
	/**
	 * In practice it prints the successful setting of the variable taken as an
	 * argument
	 * 
	 * @param var
	 *            the variable name
	 * @return the message
	 */
	public static String setOk(String var) {
		return ansi().fg(Color.WHITE).a("The " + var + " is set (" + ansi().fg(Color.CYAN).a(" OK ").reset() + ")")
				.reset().toString();
	}

	// version 1.0.9
	/**
	 * In practice it prints the successful setting of the variable taken as an
	 * argument
	 * 
	 * @param var
	 *            the variable name
	 * @return the message
	 */
	public static String setOk2(String var) {
		return ansi().fg(Color.WHITE).a("The " + var + " are set (" + ansi().fg(Color.CYAN).a(" OK ").reset() + ")")
				.reset().toString();
	}

	// version 1.0.9
	/**
	 * Print a positive message
	 * 
	 * @param msg
	 *            the message
	 * @return the positive message
	 */
	public static String positiveMsg(String msg) {
		return ansi().fg(Color.WHITE).a(msg + " (" + ansi().fg(Color.CYAN).a(" OK ").reset() + ")").reset().toString();
	}

	@Override
	public void setExitCommand(String exitCommand) {
		this.exitCommand = new ColorLocalCommand(exitCommand, "Closes the program and uninstall the components");
		((ColorLocalCommand) this.exitCommand).setExecution(new Execution() {
			@Override
			public Object exec() {
				close();
				return null;
			}
		});
		addCommand((LocalCommand) this.exitCommand);
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		super.close();
		// disattivo i componenti
		AnsiConsole.systemUninstall();
	}

	@Override
	public void useGeneralHelp() {
		ColorLocalCommand helpCommand = new ColorLocalCommand(generalHelpValue, "Show General Help commands",
				new Execution() {

					@Override
					public Object exec() {
						getHelpCommands().print();
						return getHelpCommands();
					}
				});
		// aggiungo il comando
		addCommand(helpCommand);
	}

	public ColorLocalPhaseTerminal() {
		// installo i componenti
		AnsiConsole.systemInstall();
		statusCommand = new ColorLocalCommand("status","View the report of the current phase");
		statusCommand.setExecution(new Execution() {
			@Override
			public Object exec() {
				ColorString string = new ColorString();
				if (currentPhase!=null) {
					string.append("\n\t\t|Current Phase > ").append(currentPhase.phaseName().toUpperCase(),TerminalColors.PHASE_COLOR).append("\n")
					  .append("\t\t|Level = ").append(currentPhase.getValue()+"",Color.DEFAULT).append("\n")
					  .append("\t\t|Accessible = ").append(currentPhase.isAccessible()+"",Color.DEFAULT).append("\n")
					  .append("\t\t|Satisfied = ").append(currentPhase.isSatisfied()+"",Color.DEFAULT).append("\n")
					  .append("\t\t|Supported commands = ").append(currentPhase.getCommands()+"",Color.DEFAULT);
				if ((currentPhase).getAccessibilityRule()!=null) {
					string.append("\n\t\t|Access-Rule = ").append((currentPhase).getAccessibilityRule().ruleExplanation());
				}
				if ((currentPhase).getSatisfiabilityRule()!=null) {
					string.append("\n\t\t|Satisfaction-Rule = ").append((currentPhase).getSatisfiabilityRule().ruleExplanation());
				}
				string.append("\n");
				return string.toString() ;	
				}
				else return error("NO current phase");
			}
		});
		phasesExecutorCommand = new ColorLocalCommand("phases-executor", "This command executes a phase");
		phasesExecutorCommand.setInputValueExploitable(true);
		phasesExecutorCommand.setExecution(new Execution() {

			@Override
			public Object exec() {
				Object obj = null;
				if (phasesExecutorCommand.getInputValue() != null) {
					for (Phase current : phases) {
						if (phasesExecutorCommand.getInputValue().equals(current.phaseName())) {
							obj = current.execute();
						}
					}
				} 
				else{
					if (currentPhase!=null) {
						obj = currentPhase.execute();
					}
				}
				return obj;
			}
		});
		this.pointerCommand = new ColorLocalCommand("use", "This command points to a specific phase");
		this.describerCommand = new ColorLocalCommand("describe", "This command describes a specific phase");
		this.describerCommand.setExecution(new Execution() {
			@Override
			public Object exec() {

				if (currentPhase != null) {

					StringBuffer buffer = new StringBuffer();
					buffer.append("========================================================================\n");
					buffer.append("Description of (" + j£.colors(currentPhase.phaseName(), TerminalColors.PHASE_COLOR)
							+ ")\n");
					buffer.append("========================================================================\n");

					buffer.append(((LocalPhase) currentPhase).description() + ".\n\n");

					// qui inserisco i comandi supportati di questa fase

					List<Command> commands = ((LocalPhase) currentPhase).getCommands();
					if (commands.size() > 0) {
						buffer.append("# Supported commands :\n");
					}
					for (int i = 0; i < commands.size(); i++) {

						buffer.append(
								(i + 1) + ")" + j£.colors(commands.get(i).getCommand(), TerminalColors.COMMAND_COLOR)
										+ "=" + commands.get(i).getHelp() + "\n");
					}

					buffer.append("========================================================================\n");

					return buffer.toString();
				} else {
					return null;
				}

			}
		});
		addCommands(this.pointerCommand,this.describerCommand,phasesExecutorCommand,statusCommand);
	}

	@Override
	public String getCommandRequest() {
		String text = null;
		if (getName() == null) {
			if (currentPhase != null) {

				text = "£_(" + j£.colors(currentPhase.phaseName(), TerminalColors.PHASE_COLOR) + ")_:";

			}
		} else {
			if (currentPhase != null) {

				text = getName() + "_(" + j£.colors(currentPhase.phaseName(), TerminalColors.PHASE_COLOR) + ")_:";
			}
		}
		return text;
	}

	@Override
	public LocalPhase createPhase(int value, String phaseName, String phaseDescription) {
		// verifico che non sia nessun phase con questo valore
		if (phase(value) == null && phase(phaseName) == null) {
			LocalPhase phase = new LocalPhase(phaseName, value);

			((LocalPhase) phase).setDescription(phaseDescription);

			if (value == 1) {
				startPhase = phase;
			}
			phases.add(phase);

			// qui costruisco il parametro per eseguire questa fase fino a ]

			Parameter phaseExecutionParam = phasesExecutorCommand.addParam(phase.phaseName(),
					"executes the " + £.escp(phase.phaseName()) + " phase");
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
				Parameter p_link = this.pointerCommand.addParam(phase.phaseName(), // forse non va bene
						"Go to (" + j£.colors(phase.phaseName(), TerminalColors.PHASE_COLOR) + ") phase @");
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
			Parameter p_link_desc = this.describerCommand.addParam(phase.phaseName(), "This parameter describes the ("
					+ j£.colors(phase.phaseName(), TerminalColors.PHASE_COLOR) + ") phase @");
			p_link_desc.setExecution(new Execution() {
				@Override
				public Object exec() {
					StringBuffer buffer = new StringBuffer();
					buffer.append("========================================================================\n");
					buffer.append(
							"Description of (" + j£.colors(phase.phaseName(), TerminalColors.PHASE_COLOR) + ")\n");
					buffer.append("========================================================================\n");
					buffer.append(phase.description() + ".\n\n");
					List<Command> commands = phase.getCommands();
					if (commands.size() > 0) {
						buffer.append("# Supported commands :\n");
					}
					for (int i = 0; i < commands.size(); i++) {

						buffer.append(
								(i + 1) + ")" + j£.colors(commands.get(i).getCommand(), TerminalColors.COMMAND_COLOR)
										+ "=" + commands.get(i).getHelp() + "\n");
					}
					buffer.append("========================================================================\n");
					return buffer.toString();
				}
			});
			return phase;
		} else {
			return null;
		}
	}

	@Override
	public LocalPhase createPhase(final int value, String phaseName, String phaseDescription, Command... commands) {
		// verifico che non sia nessun phase con questo valore
		if (phase(value) == null && phase(phaseName) == null) {
			LocalPhase phase = new LocalPhase(phaseName, value);

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
				Parameter p_link = this.pointerCommand.addParam(phase.phaseName(), // forse non va bene
						"Go to (" + j£.colors(phase.phaseName(), TerminalColors.PHASE_COLOR) + ") phase @");
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
			Parameter p_link_desc = this.describerCommand.addParam(phase.phaseName(), "This parameter describes the ("
					+ j£.colors(phase.phaseName(), TerminalColors.PHASE_COLOR) + ") phase @");
			p_link_desc.setExecution(new Execution() {
				@Override
				public Object exec() {
					StringBuffer buffer = new StringBuffer();
					buffer.append("========================================================================\n");
					buffer.append(
							"Description of (" + j£.colors(phase.phaseName(), TerminalColors.PHASE_COLOR) + ")\n");
					buffer.append("========================================================================\n");
					buffer.append(phase.description() + ".\n\n");
					List<Command> commands = phase.getCommands();
					if (commands.size() > 0) {
						buffer.append("# Supported commands :\n");
					}
					for (int i = 0; i < commands.size(); i++) {

						buffer.append(
								(i + 1) + ")" + j£.colors(commands.get(i).getCommand(), TerminalColors.COMMAND_COLOR)
										+ "=" + commands.get(i).getHelp() + "\n");
					}
					buffer.append("========================================================================\n");
					return buffer.toString();
				}
			});
			return phase;
		} else {
			return null;
		}
	}

}
