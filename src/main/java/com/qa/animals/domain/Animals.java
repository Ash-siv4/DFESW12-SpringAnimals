package com.qa.animals.domain;

public class Animals {

	// variables - columns in the table
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

}
