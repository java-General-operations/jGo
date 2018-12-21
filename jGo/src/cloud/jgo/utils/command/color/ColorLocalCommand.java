package cloud.jgo.utils.command.color;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.fusesource.jansi.Ansi.Color;
import cloud.jgo.j£;
import cloud.jgo.utils.command.DefaultParameter;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Parameter;
import cloud.jgo.utils.command.LocalCommand.HelpCommand;
import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.terminal.TerminalColors;

public class ColorLocalCommand extends LocalCommand {
	private ColorHelpCommand helpCommand = new ColorHelpCommand();

	public ColorLocalCommand(String command, String help) {
		super(command, help);
		// ricarico l'help colorato
		this.helpCommand.reload(this);
	}

	public ColorLocalCommand(String command, String help, Execution execution) {
		// TODO Auto-generated constructor stub
		super(command, help, execution);
		// ricarico l'help colorato
		this.helpCommand.reload(this);
	}

	@Override
	public ColorHelpCommand getHelpCommand() {
		// TODO Auto-generated method stub
		return this.helpCommand;
	}

	public static class ColorHelpCommand extends HelpCommand {
		public void reload(LocalCommand command) {
			this.command = command;
			this.buffer = new StringBuffer();
			buffer.append("===================================================================================\n");
			if (this.command.getBelongsTo() != null) {
				buffer.append("HELP Of " + "\"" + j£.colors(this.command.getCommand(), TerminalColors.COMMAND_COLOR)
						+ "\" - Phase :"
						+ j£.colors(this.command.getBelongsTo().phaseName(), TerminalColors.PHASE_COLOR) + "\n");
			} else {
				buffer.append("HELP Of " + "\"" + j£.colors(this.command.getCommand(), TerminalColors.COMMAND_COLOR)
						+ "\" - Phase :" + j£.colors("absent", Color.DEFAULT) + "\n");
			}
			buffer.append("===================================================================================\n");
			// qui devo prendere tutti i parameters
			Collection<Parameter> collection = command.getStructure().values();
			List<Parameter> orderParameters = command.sortParameters();
			// qui ci sarà la descrizione del comando root
			buffer.append(this.command.getHelp().toUpperCase() + "\n\n");
			if (orderParameters != null) {
				buffer.append("* Parameters :" + orderParameters + " :\n\n");
				if (this.command.hasParameters()) {
					// ci sono parametri
					// quindi qui devo prendere i params
					Iterator<Parameter> iterator = orderParameters.iterator();
					while (iterator.hasNext()) {
						Parameter param = iterator.next();
						buffer.append(j£.colors(param.getParam(), TerminalColors.PARAMETER_COLOR) + "="
								+ param.getParameterHelp() + "  / has input value =" + param.hasInputValueExploitable()
								+ "\n");
					}
				}
			} else {
				buffer.append("* Parameters :" + collection + " :\n\n");
				if (this.command.hasParameters()) {
					// ci sono parametri
					// quindi qui devo prendere i params
					Iterator<Entry<String, Parameter>> iterator = command.iterator();
					while (iterator.hasNext()) {
						Map.Entry<java.lang.String, cloud.jgo.utils.command.Parameter> entry = (Map.Entry<java.lang.String, cloud.jgo.utils.command.Parameter>) iterator
								.next();
						Parameter param = entry.getValue();
						buffer.append(j£.colors(param.getParam(), TerminalColors.PARAMETER_COLOR) + "="
								+ param.getParameterHelp() + "  / has input value =" + param.hasInputValueExploitable()
								+ "\n");
					}
				}
			}
		}
	}

}
