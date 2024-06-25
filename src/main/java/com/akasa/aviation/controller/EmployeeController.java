package com.akasa.aviation.controller;

import com.akasa.aviation.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/pilots")
    public String getAllEmployees(Model model) {
        model.addAttribute("pilots", personRepository.findBydaysremainingLessThan(7));
        return "pilotList.html"; // Thymeleaf template name (employee-list.html)
    }
}