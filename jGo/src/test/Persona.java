package test;


import javax.xml.bind.annotation.XmlRootElement;

import org.fusesource.jansi.Ansi.Color;

import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.annotations.£Command;
import cloud.jgo.utils.command.color.ColorLocalCommand;
@£Command(help = "Creates a Person", involveAll=true)
public class Persona {

	final String PERSON_="person"
			+ "";
	private String nome ;
	private String cognome ;
	private int eta ;
	private Double stipendio ;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public Double getStipendio() {
		return stipendio;
	}
	public void setStipendio(Double stipendio) {
		this.stipendio = stipendio;
	}
	public Persona() {
		super();
		this.nome = null ;
		this.cognome = null ;
		this.eta = 0 ;
		this.stipendio = new Double(0);
	}
	public Persona(String nome, String cognome, int età) {
		this.nome = nome;
		this.cognome = cognome;
		this.eta = età;
		this.stipendio = new Double(0);
	}
	public int getEtà() {
		return eta;
	}
	public void setEtà(int età) {
		this.eta = età;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result = null ;
		try {
			result =  ColorLocalCommand.toString(this, Color.DEFAULT, Color.GREEN);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result ;
	}
}
