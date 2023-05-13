package com.example.coursework2;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    private final QuestionService questionService = new JavaQuestionService();


    @BeforeEach
    public void before() {
        questionService.add(new Question("quest1", "answer1"));
        questionService.add(new Question("quest2", "answer2"));
        questionService.add(new Question("quest3", "answer3"));
    }

    @AfterEach
    public void after() {
        Collection<Question> questions = new ArrayList<>(questionService.getAll());
        questions.forEach(questionService::remove);
    }

    @Test
    public void addTest() {
        after();
        int count = questionService.getAll().size();
        Question excepted = new Question("quest4", "answer4");
        Question actual = questionService.add("quest4", "answer4");

        assertEquals(excepted, actual);
        assertThat(actual).isEqualTo(excepted).isIn(questionService.getAll());
        assertThat(questionService.getAll()).hasSize(count + 1);
    }

    @Test
    public void addTwoTest() {
        after();
        int count = questionService.getAll().size();
        Question excepted = new Question("quest4", "answer4");
        Question actual = questionService.add(new Question("quest4", "answer4"));

        assertEquals(excepted, actual);
        assertThat(actual).isEqualTo(excepted).isIn(questionService.getAll());
        assertThat(questionService.getAll()).hasSize(count + 1);
    }

    @Test
    public void addTwoNegativeTest() {
        assertThatExceptionOfType(QuestionBadRequestException.class)
                .isThrownBy(() -> questionService.add(new Question("quest1", "answer1")));
    }

    @Test
    public void removeTest() {
        int count = questionService.getAll().size();
        Question excepted = new Question("quest2", "answer2");
        Question actual = questionService.remove(new Question("quest2", "answer2"));

        assertEquals(excepted, actual);
        assertThat(actual).isEqualTo(excepted).isNotIn(questionService.getAll());
        assertThat(questionService.getAll()).hasSize(count - 1);
    }

    @Test
    public void removeTestNegative() {
        assertThatExceptionOfType(QuestionBadRequestException.class)
                .isThrownBy(() -> questionService.remove(new Question("quest4", "answer4")));
    }

    @Test
    public void getAllTest() {
        assertThat(questionService.getAll()).hasSize(3)
                .containsExactlyInAnyOrder(
                        new Question("quest1", "answer1"),
                        new Question("quest2", "answer2"),
                        new Question("quest3", "answer3")
                );
    }

    @Test
    public void getRandomQuestionTest() {
        assertThat(questionService.getRandomQuestion())
                .isNotNull()
                .isIn(questionService.getAll());
    }

    @Test
    public void getRandomQuestionNegativeTest() {
        after();
        assertThatExceptionOfType(QuestionBadRequestException.class)
                .isThrownBy(questionService::getRandomQuestion);
    }


}