package com.qa.animals.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // marking this class as a table for SQL
public class Animals {
	// create table animals(
	// id int not null auto_increment,
	// breed varchar(255),
	// age int,
	// gender varchar(255),
	// primary key(id));

	// variables - columns in the table
	@Id // marks the field below as a primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
	private Long id;

	private String breed;
	private int age;
	private String gender;

	// constructors - 3 types
	// overloading - polymorphism

	// 1 - default constructor - empty one
	public Animals() {
		super();
	}

	// 2 - constructor with id

	public Animals(Long id, String breed, int age, String gender) {
		super();
		this.id = id;
		this.breed = breed;
		this.age = age;
		this.gender = gender;
	}

	// 3 - constructor without id

	public Animals(String breed, int age, String gender) {
		super();
		this.breed = breed;
		this.age = age;
		this.gender = gender;
	}

	// getters & setters

	public String getBreed() {
		return breed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, breed, gender, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animals other = (Animals) obj;
		return age == other.age && Objects.equals(breed, other.breed) && Objects.equals(gender, other.gender)
				&& Objects.equals(id, other.id);
	}

}
