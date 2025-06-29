package edu.javacourse.student.controller;

import edu.javacourse.student.domain.University;
import edu.javacourse.student.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/universities")
public class UniversitiesController {
    @Autowired
    private UniversityService service;

    @GetMapping
    public String findUniversities(Model model){
        List<University> list = service.getUniversities();
        model.addAttribute("today", LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        model.addAttribute("universities", list);

        return "universityList";
    }
}
