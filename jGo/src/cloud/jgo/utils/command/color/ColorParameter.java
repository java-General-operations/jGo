package cloud.jgo.utils.command.color;

import org.fusesource.jansi.Ansi.Color;

import cloud.jgo.utils.command.DefaultParameter;
import cloud.jgo.utils.command.execution.Execution;

public class ColorParameter extends DefaultParameter{
	
	public static Color color = Color.DEFAULT;

	public ColorParameter(String param, String help) {
		super(param, help);
		// TODO Auto-generated constructor stub
	}
	
	public ColorParameter(String param, String help, Execution execution) {
		// TODO Auto-generated constructor stub
		super(param, help, execution);
	}

}
