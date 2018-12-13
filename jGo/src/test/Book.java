package test;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import cloud.jgo.net.tcp.http.jor.JOR;
import cloud.jgo.net.tcp.http.jor.ResponseType;

@JOR(field_id = "title+author", url_Pattern = "/books", responseType = ResponseType.XML)
@XmlRootElement
public class Book {
	private int numberPages;
	private String author;
	private double price;
	private String title;

	public Book(int numberPages, String author, double price, String title) {
		super();
		this.numberPages = numberPages;
		this.author = author;
		this.price = price;
		this.title = title;
	}

	public Book() {
		// TODO Auto-generated constructor stub
		super();
		this.numberPages = -1;
		this.author = null;
		this.price = -1;
		this.title = null;
	}

	@XmlElement
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlElement
	public int getNumberPages() {
		return numberPages;
	}

	public void setNumberPages(int numberPages) {
		this.numberPages = numberPages;
	}

	@XmlElement
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@XmlElement
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
