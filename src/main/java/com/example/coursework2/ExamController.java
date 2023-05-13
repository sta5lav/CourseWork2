package com.example.coursework2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ExamController {

   private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }


    @GetMapping(path = "get/{amount}")
    public Collection<Question> getQuestions(@PathVariable() int amount) {
       return examinerService.getQuestions(amount);
    }
}
