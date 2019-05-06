package cloud.jgo.test;

import java.io.Serializable;

import cloud.jgo.net.tcp.http.jor.annotations.JOR;

/**
 * @author Martire91 - <https://github.com/wasp91>
 * @version 1.0.0
 */
@JOR(field_id = "nome", url_Pattern = "magazzini")
public class Magazzino implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private int numeroProdotti;

	public Magazzino(String nome, int numeroProdotti) {
		super();
		this.nome = nome;
		this.numeroProdotti = numeroProdotti;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumeroProdotti() {
		return numeroProdotti;
	}

	public void setNumeroProdotti(int numeroProdotti) {
		this.numeroProdotti = numeroProdotti;
	}
	
}
