package quiz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import quiz.entity.Answer;
import quiz.entity.Question;
import quiz.entity.QuizKit;
import quiz.repositories.AnswerRepository;
import quiz.repositories.QuestionRepository;
import quiz.repositories.QuizKitRepository;

import javax.annotation.PostConstruct;


@Component
@RequiredArgsConstructor
public class TestDataService {

    private final QuizKitRepository quizKitRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @PostConstruct
    protected void init() {
        QuizKit kit1 = QuizKit.builder().kitNo(1).description("test 1").questionCount(2).build();
        QuizKit kit2 = QuizKit.builder().kitNo(2).description("test 2").questionCount(1).build();
        quizKitRepository.save(kit1);
        quizKitRepository.save(kit2);

        Question question1 = Question.builder().description("question 1").questionNo(0).quizKitId(kit1.getId()).build();
        Question question2 = Question.builder().description("question 2").questionNo(1).quizKitId(kit1.getId()).build();
        Question question3 = Question.builder().description("question 3").questionNo(0).quizKitId(kit2.getId()).build();

        questionRepository.save(question1);
        questionRepository.save(question2);
        questionRepository.save(question3);

        Answer answer1 = Answer.builder().text("ans1").answerNo(1).questionId(question1.getId()).isCorrect(false).build();
        Answer answer2 = Answer.builder().text("ans2").answerNo(2).questionId(question1.getId()).isCorrect(true).build();
        Answer answer3 = Answer.builder().text("ans3").answerNo(1).questionId(question2.getId()).isCorrect(false).build();
        Answer answer4 = Answer.builder().text("ans4").answerNo(2).questionId(question2.getId()).isCorrect(true).build();
        Answer answer5 = Answer.builder().text("ans5").answerNo(1).questionId(question3.getId()).isCorrect(false).build();
        Answer answer6 = Answer.builder().text("ans6").answerNo(2).questionId(question3.getId()).isCorrect(true).build();

        answerRepository.save(answer1);
        answerRepository.save(answer2);
        answerRepository.save(answer3);
        answerRepository.save(answer4);
        answerRepository.save(answer5);
        answerRepository.save(answer6);
    }

}
