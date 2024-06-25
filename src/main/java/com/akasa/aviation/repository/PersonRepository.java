package com.akasa.aviation.repository;

import com.akasa.aviation.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
//    List<Person> findByAgeGreaterThan(Integer age);
    List<Person> findBydaysremainingLessThan(Integer daysremaining);
}