package test;

import cloud.jgo.utils.command.annotations.Command;
import cloud.jgo.utils.command.annotations.Parameter;

@Command(help = "Creates a Person")
public class Person {

	@Parameter(help = "sets person name")
	private String nome ;
	@Parameter(help = "sets person surname")
	private String cognome ;
	@Parameter(help = "sets person age")
	private int età ;
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
	public Person() {
		super();
	}
	public Person(String nome, String cognome, int età) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.età = età;
	}
	public int getEtà() {
		return età;
	}
	public void setEtà(int età) {
		this.età = età;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nome+" "+this.cognome+" "+this.età;
	}
}
