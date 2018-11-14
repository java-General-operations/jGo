package test;

import cloud.jgo.net.tcp.http.jor.JOR;
// /persone/nome-cognome-età
@JOR(field_id = "nome", url_Pattern = "/persone")
public class Persona {
	private String nome ;
	private String cognome ;
	private int età;
	public Persona(String nome, String cognome,int età) {
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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nome+" "+this.cognome ;
	}
	
}
