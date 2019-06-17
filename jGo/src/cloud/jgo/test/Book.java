package cloud.jgo.test;

import cloud.jgo.net.tcp.http.jor.annotations.JOR;

/**
 * @author Martire91 - <https://github.com/wasp91>
 */
@JOR(field_id = "title", url_Pattern = "books/")
public class Book {

	private String title, author;
	private double price;

	public Book() {
		this.title = null;
		this.author = null;
		this.price = -1;
	}

	public Book(String title, String author, double price) {
		super();
		this.title = title;
		this.author = author;
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
