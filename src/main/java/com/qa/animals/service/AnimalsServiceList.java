package com.qa.animals.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.animals.domain.Animals;

@Service
public class AnimalsServiceList implements AnimalsInterface {
	// Store info in, alternative to db:
	private List<Animals> animals = new ArrayList<>();

	@Override
	public Animals create(Animals x) {
		x.setId((long) this.animals.indexOf(x));// added this to update id variable with array index
		this.animals.add(x);
		Animals created = this.animals.get(this.animals.size() - 1);
		return created;
	}

	@Override
	public List<Animals> read() {
		// TODO Auto-generated method stub
		return this.animals;
	}

	public Animals readOne(int id) {
		// TODO Auto-generated method stub
		return this.animals.get(id);
	}

	@Override
	public Animals update(int id, Animals y) {
		// TODO Auto-generated method stub
		this.animals.set(id, y);
		return this.animals.get(id);
	}

	@Override
	public Animals delete(int id) {
		// TODO Auto-generated method stub
		return this.animals.remove(id);
	}

}
