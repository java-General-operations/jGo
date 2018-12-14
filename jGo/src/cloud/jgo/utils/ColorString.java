package cloud.jgo.utils;

import org.fusesource.jansi.Ansi.Color;
import org.fusesource.jansi.AnsiConsole;

import cloud.jgo.£;

// version 2.0.0
public class ColorString {
	private StringBuffer buffer ;
	public int length ;
	public ColorString(String string,Color color) {
		// TODO Auto-generated constructor stub
		AnsiConsole.systemInstall();
		this.buffer = new StringBuffer();
		this.buffer.append(£.colors(string, color));
		this.length = this.buffer.length();
	}
	public ColorString() {
		// TODO Auto-generated constructor stub
		AnsiConsole.systemInstall();
		this.buffer = new StringBuffer();
		this.length = this.buffer.length();
	}
	public ColorString append(String text,Color color) {
		this.buffer.append(£.colors(text, color));
		// aggiorno la lunghezza
		this.length = this.buffer.length();
		return this ;
	}
	public ColorString append(char charat,Color color) {
		this.buffer.append(£.colors(""+charat, color));
		this.length = this.buffer.length();
		return this ;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.buffer.toString();
	}
	public String getValue() {
		return this.buffer.toString();
	}
	
	public void setValue(String value,Color color) {
		// sostituisco l'intero valore del buffer
		this.buffer = new StringBuffer(£.colors(value, color));
		this.length = this.buffer.length();
	}
}
