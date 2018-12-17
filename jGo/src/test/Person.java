package test;

public class Person {

	private String name, surname;
	private int age;

	public Person() {
		// TODO Auto-generated constructor stub
		this.name = null;
		this.surname = null;
		this.age = 0;
	}

	public Person(String name, String surname, int age) {
		super();
		this.name = name;
		this.surname = surname;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("===============================================================================\n");
		buffer.append("* Name="+this.name+"\t\t"+"* Surname="+this.surname+"\n* Age="+this.age+"\n");
		buffer.append("===============================================================================");
		return buffer.toString();
	}

}
