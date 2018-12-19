package cloud.jgo.utils.command.terminal.phase;

import org.fusesource.jansi.Ansi.Color;

public class ColorPhase extends DefaultPhase{
	
	public static Color color = Color.DEFAULT;

	public ColorPhase(String phaseType, int value) {
		super(phaseType, value);
		// TODO Auto-generated constructor stub
	}

}
