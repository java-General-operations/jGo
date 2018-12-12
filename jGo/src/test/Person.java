package test;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import cloud.jgo.net.tcp.http.jor.JOR;
import cloud.jgo.net.tcp.http.jor.ResponseType;

@JOR(field_id = "name+surname", url_Pattern = "/people", responseType=ResponseType.XML)
@XmlRootElement
public class Person {
	
	private String name, surname;
	private int age;

	public Person(String name, String surname, int age) {
		super();
		this.name = name;
		this.surname = surname;
		this.age = age;
	}
	public Person() {
		// TODO Auto-generated constructor stub
		this.name = null ;
		this.surname = null ;
	}
	@XmlElement
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	@XmlElement
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return this.name+" "+this.surname+" "+this.age ;
	}
}
