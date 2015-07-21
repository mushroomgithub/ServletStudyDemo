package cn.lnu.beanutils;

import java.util.Date;

public class Person {//javabean  提供了get/set方法的字段叫做属性，注意现隐藏的一个class属性，因为所有类都继承自Object,这个类有一个getClass方法，所以还隐含存在一个Class属性
	
	private String name;//字段
	private int password;
	private int age;
	private Date birthday;
	
	
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
