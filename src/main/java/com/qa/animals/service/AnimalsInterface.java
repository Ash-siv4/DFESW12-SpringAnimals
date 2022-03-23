package com.qa.animals.service;

import java.util.List;

import com.qa.animals.domain.Animals;

public interface AnimalsInterface<T> {
	
	//abstract methods for each of the CRUD
	
	Animals create(Animals x);
	
	List<Animals> read();
	
	Animals update(T id, Animals y);
	
	Animals delete(T id);

}
