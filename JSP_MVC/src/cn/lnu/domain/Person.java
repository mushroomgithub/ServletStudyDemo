package cn.lnu.domain;

import java.util.Date;

public class Person {
	private String name="mushroom";
	private int age;
	private Date birthday;
	
	private Address address;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Person(String name) {
		super();
		this.name = name;
	}
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
