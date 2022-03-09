package com.qa.animals.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.animals.domain.Animals;
import com.qa.animals.repo.AnimalsRepo;

@Service
public class AnimalsServiceDB implements AnimalsInterface<Long> {

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
		return this.repo.findAll();
	}
	
	
	public Animals readOne(Long id) {
		// TODO Auto-generated method stub
		Optional<Animals> optRead = this.repo.findById(id);
		return optRead.orElse(null);
	}

	@Override
	public Animals update(Long id, Animals y) {
		// TODO Auto-generated method stub
		Optional<Animals> optAni =  this.repo.findById(id);
		Animals found = optAni.get();
		found.setAge(y.getAge());
		found.setBreed(y.getBreed());
		found.setGender(y.getGender());
		return this.repo.save(found);
	}

	//return object deleted to confirm info
	@Override
	public Animals delete(Long id) {
		Optional<Animals> toDelete = this.repo.findById(id);
		this.repo.deleteById(id);
		return toDelete.orElse(null);
	}

	//boolean to return true or false if it deleted
	public boolean remove(Long id) {//id = 1
		this.repo.deleteById(id);//id = 1 - gone
		return !this.repo.existsById(id);//false - true
	}
	
	
}
