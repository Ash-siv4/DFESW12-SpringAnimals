package com.qa.animals.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.animals.domain.Animals;
import com.qa.animals.repo.AnimalsRepo;

@SpringBootTest
@ActiveProfiles("test")
public class AnimalsServiceDBTest {
	
	@Autowired //the class we are testing
	private AnimalsServiceDB serv;
	
	@MockBean //this is the class we are dependent on so have to mock
	private AnimalsRepo rep;
	

	@BeforeEach
	void setUp() {
		System.out.println("before");
	}
	
	@Test
	void testCreate() {
		System.out.println("test 1");
		//GIVEN
		// input/data
		Animals input = new Animals("Reptile", 10, "Male");
		Animals returned = new Animals(1L,"Reptile", 10, "Male");
		//WHEN
		Mockito.when(this.rep.save(input)).thenReturn(returned);
		//THEN
		assertThat(this.serv.create(input)).isEqualTo(returned);
		
		//Verification
		Mockito.verify(this.rep, Mockito.times(1)).save(input);
	}
	
	@Test
	void testRead() {
		System.out.println("test 2");
	}
	
	@AfterEach
	void clear() {
		System.out.println("after");
	}
	
}
