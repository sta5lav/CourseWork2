package com.example.coursework2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private  ExaminerServiceImpl examinerService;

    private final Set<Question> questions = Set.of(
            new Question("quest1", "answer1"),
            new Question("quest2", "answer2"),
            new Question("quest3", "answer3"),
            new Question("quest4", "answer4"),
            new Question("quest5", "answer5")
    );
    @Test
    public void getQuestionsTest() {
        when(questionService.getAll()).thenReturn(questions);
        when(questionService.getRandomQuestion())
                .thenReturn(
                        new Question("quest1", "answer1"),
                        new Question("quest3", "answer3"),
                        new Question("quest2", "answer2"),
                        new Question("quest4", "answer4"),
                        new Question("quest3", "answer3")
                );
        assertThat(examinerService.getQuestions(4))
                .containsExactlyInAnyOrder(
                        new Question("quest1", "answer1"),
                        new Question("quest2", "answer2"),
                        new Question("quest3", "answer3"),
                        new Question("quest4", "answer4")
                );
    }

    @Test
    public void getQuestionsNegativeTest() {
        assertThatExceptionOfType(IncorrectAmountOfQuestionException.class)
                .isThrownBy(() -> examinerService.getQuestions((-1)));

        when(questionService.getAll()).thenReturn(questions);

        assertThatExceptionOfType(IncorrectAmountOfQuestionException.class)
                .isThrownBy(() -> examinerService.getQuestions(questions.size() + 1));
    }
}