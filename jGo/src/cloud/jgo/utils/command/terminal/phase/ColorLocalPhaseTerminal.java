package cloud.jgo.utils.command.terminal.phase;

import java.util.List;

import cloud.jgo.j£;
import cloud.jgo.utils.command.Command;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Parameter;
import cloud.jgo.utils.command.color.ColorLocalCommand;
import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.terminal.LocalTerminal;
import cloud.jgo.utils.command.terminal.TerminalColors;

public class ColorLocalPhaseTerminal extends LocalPhaseTerminal{
	
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
	public void useGeneralHelp() {
		ColorLocalCommand helpCommand = new ColorLocalCommand(generalHelpValue, "Show General Help commands", new Execution() {

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
		this.pointerCommand = new ColorLocalCommand("use", "This command points to a specific phase");
		this.describerCommand = new ColorLocalCommand("describe","This command describes a specific phase");
		this.describerCommand.setExecution(new Execution() {
			@Override
			public Object exec() {

				if (currentPhase != null) {

					StringBuffer buffer = new StringBuffer();
					buffer.append("========================================================================\n");
					buffer.append("Description of (" + j£.colors(currentPhase.phaseName(),
							TerminalColors.PHASE_COLOR) + ")\n");
					buffer.append("========================================================================\n");

					buffer.append(((DefaultPhase) currentPhase).description() + ".\n\n");

					// qui inserisco i comandi supportati di questa fase

					List<Command> commands = ((DefaultPhase) currentPhase).getCommands();
					if (commands.size() > 0) {
						buffer.append("# Supported commands :\n");
					}
					for (int i = 0; i < commands.size(); i++) {

						buffer.append(
								(i + 1) + ")" + j£.colors(commands.get(i).getCommand(),TerminalColors.COMMAND_COLOR) + "=" + commands.get(i).getHelp() + "\n");
					}

					buffer.append("========================================================================\n");

					return buffer.toString();
				} else {
					return null;
				}

			}
		});
		addCommand(this.pointerCommand);
		addCommand(this.describerCommand);
	}

	@Override
	public String getCommandRequest() {
		String text = null;
		if (getName() == null) {
			if (currentPhase != null) {

				text = "£_("
						+ j£.colors(currentPhase.phaseName(), TerminalColors.PHASE_COLOR)
						+ ")_:";

			}
		} else {
			if (currentPhase != null) {

				text = getName() + "_("
						+ j£.colors(currentPhase.phaseName(), TerminalColors.PHASE_COLOR)
						+ ")_:";
			}
		}
		return text;
	}
	
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

			// qui solo se non è la fase start, perchè non ci serve avere un riferimento a
			// tale fase
			// eseguiamo il codice dell'aggiungimento link param. Tutto questo perchè
			// abbiamo il metodo reset
			// e il comando che si occupa di resettare alla prima fase la struttura
			if (value > 1) { // diamo per scontato che la prima fase è la numero 1
				// okok qui completo il metodo
				// aggiungo al puntatore un link che accederà a questa fase
				Parameter p_link = this.pointerCommand.addParam(phase.phaseName(), // forse non va bene
						"Go to ("
								+ j£.colors(phase.phaseName(),  TerminalColors.PHASE_COLOR)
								+ ") phase @");
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
			Parameter p_link_desc = this.describerCommand.addParam(phase.phaseName(),
					"This parameter describes the ("
							+ j£.colors(phase.phaseName(),  TerminalColors.PHASE_COLOR)
							+ ") phase @");
			System.out.println("Parametro del comando che descrive creato @");
			p_link_desc.setExecution(new Execution() {
				@Override
				public Object exec() {
					StringBuffer buffer = new StringBuffer();
					buffer.append(
							"=================================================================================================\n");
					buffer.append("Description of ("
							+ j£.colors(phase.phaseName(), TerminalColors.PHASE_COLOR)
							+ ")\n");
					buffer.append(
							"==================================================================================================\n");
					buffer.append(phase.description() + ".\n");
					List<Command> commands = phase.getCommands();
					if (commands.size() > 0) {
						buffer.append("# Supported commands :");
					}
					buffer.append(commands + "\n");
					buffer.append(
							"====================================================================================================\n");
					return buffer.toString();
				}
			});
			return phase;
		} else {
			return null;
		}
	}

}
