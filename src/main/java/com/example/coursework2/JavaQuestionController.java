package com.example.coursework2;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Collection;


@RestController
@RequestMapping(path = "/java")
public class JavaQuestionController {

    QuestionService service;

    public JavaQuestionController(QuestionService service) {
        this.service = service;
    }


    @GetMapping(path = "/add")
    public Question addQuestion(@PathParam("question") String question, @PathParam("answer") String answer) {
        return service.add(question, answer);
    }

    @GetMapping(path = "/remove")
    public Question removeQuestion(@PathParam("question") String question, @PathParam("answer") String answer) {
        return service.remove(new Question(question, answer));
    }

    @GetMapping
    public Collection<Question> getQuestions() {
       return service.getAll();
    }
}
