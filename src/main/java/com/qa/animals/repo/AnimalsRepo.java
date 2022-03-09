package com.qa.animals.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.animals.domain.Animals;

@Repository
public interface AnimalsRepo extends JpaRepository<Animals, Long> {

}
