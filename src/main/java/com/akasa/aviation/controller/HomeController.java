package com.akasa.aviation.controller;

import com.akasa.aviation.model.Person;
import com.akasa.aviation.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private PersonRepository personRepository;
    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @PostMapping("/test-save")
    public ResponseEntity<String> testSave() {
        Person person = new Person();
        person.setName("Test User");
        person.setAge(30);
        person.setEmail("test@example.com");

        personRepository.save(person);
        return ResponseEntity.ok("Test user saved successfully.");
    }
}