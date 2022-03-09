package com.qa.animals.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.animals.domain.Animals;
import com.qa.animals.repo.AnimalsRepo;

@Service
public class AnimalsServiceDB implements AnimalsInterface {

	private AnimalsRepo repo; //inject dependency
	
	public AnimalsServiceDB(AnimalsRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Animals create(Animals x) {
		// TODO Auto-generated method stub
		return this.repo.save(x);
	}

	@Override
	public List<Animals> read() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Animals readOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Animals update(int id, Animals y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Animals delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
