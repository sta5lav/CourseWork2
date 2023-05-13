package com.example.coursework2;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions = new HashSet<>();

    private final Random random = new Random();

    @Override
    public Question add(String question, String answer) {
        Question q = new Question(question, answer);
        add(q);
        return q;
    }

    @Override
    public Question add(Question question) {
        if (questions.add(question)) {
            return question;
        }
        throw new QuestionBadRequestException();
    }

    @Override
    public Question remove(Question question) {
        if (!questions.remove(question)){
            throw new QuestionBadRequestException();
            }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }

    @Override
    public Question getRandomQuestion() {
        return questions.stream()
                .skip(questions.isEmpty() ? 0 : random.nextInt(questions.size()))
                .findFirst()
                .orElseThrow(QuestionBadRequestException::new);
    }
}
