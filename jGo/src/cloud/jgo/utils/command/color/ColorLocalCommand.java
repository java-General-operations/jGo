package cloud.jgo.utils.command.color;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.fusesource.jansi.Ansi.Color;

import cloud.jgo.j£;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Parameter;
import cloud.jgo.utils.command.LocalCommand.HelpCommand;
import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.terminal.phase.ColorPhase;

public class ColorLocalCommand extends LocalCommand {

	public static Color color = Color.DEFAULT;
	private ColorHelpCommand helpCommand = new ColorHelpCommand();

	public ColorLocalCommand(String command, String help) {
		super(command, help);
		// TODO Auto-generated constructor stub
	}
	public ColorLocalCommand(String command, String help, Execution execution) {
		// TODO Auto-generated constructor stub
		super(command, help, execution);
	}
	
	@Override
	public ColorHelpCommand getHelpCommand() {
		// TODO Auto-generated method stub
		return this.helpCommand ;
	}

	public static class ColorHelpCommand extends HelpCommand {
		public void reload(LocalCommand command) {
			this.command = command;
			this.buffer = new StringBuffer();
			buffer.append("===================================================================================\n");
			if (this.command.getBelongsTo() != null) {
				buffer.append("HELP Of " + "\"" + j£.colors(this.command.getCommand(), color) + "\" - Phase :"
						+ j£.colors(this.command.getBelongsTo().phaseName(), ColorPhase.color) + "\n");
			} else {
				buffer.append("HELP Of " + "\"" + j£.colors(this.command.getCommand(), ColorLocalCommand.color)
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
						buffer.append(j£.colors(param.getParam(), ColorParameter.color) + "=" + param.getParameterHelp()
								+ "  / has input value =" + param.hasInputValueExploitable() + "\n");
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
						buffer.append(j£.colors(param.getParam(), ColorParameter.color) + "=" + param.getParameterHelp()
								+ "  / has input value =" + param.hasInputValueExploitable() + "\n");
					}
				}
			}
		}
	}

}
