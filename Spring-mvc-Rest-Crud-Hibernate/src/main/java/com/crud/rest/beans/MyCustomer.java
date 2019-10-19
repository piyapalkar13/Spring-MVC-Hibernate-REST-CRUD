package com.crud.rest.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="mycustomer")
@JsonIgnoreProperties({"hibernateLazyInitailizer","handler"}) //very imp
public class MyCustomer
{

	@Id
	@GeneratedValue
	private int id ;
	
	private String name;
	
	private int age;
	
	private String email;
	
	private String city;

	//default
	public MyCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//para
	public MyCustomer(int id, String name, int age, String email, String city) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.email = email;
		this.city = city;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
	
	
	
	
	
	
}
