package com.qa.animals.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.animals.domain.Animals;
import com.qa.animals.repo.AnimalsRepo;

@SpringBootTest //lets spring know this is a test class
@ActiveProfiles("test")//knows to connect to h2
public class AnimalsServiceDBTest {

	@MockBean //mocks the class/interface below
	private AnimalsRepo r;
	
	@Autowired //class testing
	private AnimalsServiceDB s;
	
	private Animals input;
	private Animals returned;
	
	@BeforeEach
	void setUp() {
		input = new Animals("reptile", 10, "m");
		returned = new Animals(1L, "reptile", 10, "m");
	}
	
	
	@Test
	void createTest() {
		//GIVEN - THE DATA YOU NEED (INPUT INTO THE METHOD)
//		Animals input = new Animals("reptile", 10, "m");
		//WHEN -m what needs to be mocked
		Mockito.when(this.r.save(input)).thenReturn(input);
		//THEN - what to test
		assertThat(this.s.create(input)).isEqualTo(input);
		//Verification - for methods you can't test (e.g: void methods)
		Mockito.verify(this.r, Mockito.times(1)).save(input);
	}
	
	@Test
	void deleteTest() {
		//GIVEN
		Long id = 1L;
		//Optional of animal
		Optional<Animals> opt = Optional.of(returned);
		//WHEN 
		Mockito.when(this.r.findById(id)).thenReturn(opt);
		//THEN
		assertThat(this.s.delete(id)).isEqualTo(returned);
		//verify
		Mockito.verify(this.r, Mockito.times(1)).deleteById(id);
	}
	
	@Test
	void removeTest() {
		//Given
		Long id = 1L;
		//when
		Mockito.when(this.r.existsById(id)).thenReturn(false);
		//then
		assertThat(this.s.remove(id)).isTrue();
		//verify
		Mockito.verify(this.r,Mockito.times(1)).deleteById(id);
	}
	

}
