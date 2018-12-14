package test;

import org.fusesource.jansi.Ansi.Color;

import cloud.jgo.£;
import cloud.jgo.utils.ColorString;

public class GeneralTest {
public static void main(String[] args) {
	
	
	
	ColorString colorString = new ColorString();
	
	colorString.append("Ciao ",Color.BLUE).append("Cosenza",Color.RED).append(" come stai",Color.MAGENTA);
	
	colorString.append("\n",Color.DEFAULT);
	
	colorString.append('(',Color.GREEN).append("@",Color.MAGENTA).append(")",Color.GREEN);
	
	
	System.out.println(colorString);
	
	
	
}
}
