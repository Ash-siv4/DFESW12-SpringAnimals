package com.qa.animals.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.animals.domain.Animals;

@RestController
public class AnimalsController {

	//Store info in, alternative to db:
	private List<Animals> animals = new ArrayList<>();
	
	//CRUD 
	
	//CREATE
	@PostMapping("/create")
	public void createAnimal(@RequestBody Animals a) {
		this.animals.add(a);
	}
	
	//READ
	@GetMapping("readAll")
	public List<Animals> readAnimal(){
		return this.animals;
	}
	
	//READ BY ID
	
	//UPDATE
	
	//DELETE
	
	
}
