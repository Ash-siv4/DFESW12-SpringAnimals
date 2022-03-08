package com.qa.animals.rest;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.animals.domain.Animals;

@RestController
public class AnimalsController {

	// Store info in, alternative to db:
	private List<Animals> animals = new ArrayList<>();

	// CRUD

	// CREATE
	@PostMapping("/create")
	public ResponseEntity<Animals> createAnimal(@RequestBody Animals a) {
		a.setId((long) this.animals.indexOf(a));// added this to update id variable with array index
		this.animals.add(a);
		Animals created = this.animals.get(this.animals.size()-1);
		return new ResponseEntity<Animals>(created, HttpStatus.CREATED);
	}

	// READ
	@GetMapping("readAll")
	public List<Animals> readAnimal() {
		return this.animals;
	}

	// READ BY ID
	@GetMapping("/readById/{id}")
	public Animals getById(@PathVariable int id) {
		return this.animals.get(id);
	}

	// UPDATE
	@PutMapping("/update/{id}")
	public Animals update(@PathVariable int id, @RequestBody Animals updated) {
		this.animals.set(id, updated);
		return this.animals.get(id);
	}

	// DELETE
	@DeleteMapping("/delete")
	public void delete(@PathParam("id") int id) {
		this.animals.remove(id);
	}

}
