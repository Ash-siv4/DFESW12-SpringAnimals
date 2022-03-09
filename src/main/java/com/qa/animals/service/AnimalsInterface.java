package com.qa.animals.service;

import java.util.List;

import com.qa.animals.domain.Animals;

public interface AnimalsInterface {
	
	//abstract methods for each of the CRUD
	
	Animals create(Animals x);
	
	List<Animals> read();
	
	Animals update(int id, Animals y);
	
	Animals delete(int id);

}
