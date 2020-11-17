package testing.core.repository;


import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import testing.core.entity.Question;
import testing.core.entity.Quiz;
import testing.core.entity.types.QuestionDifficult;
import testing.core.entity.types.QuestionType;

import javax.transaction.Transactional;
import java.util.List;


@DataJpaTest
@RunWith(SpringRunner.class)
public class QuizRepositoryTest {

  @Autowired
  private QuestionRepository questionRepository;
  @Autowired
  private QuizRepository quizRepository;
  @Autowired
  private QuizQuestionRepository quizQuestionRepository;

  @After
  public void tearDown() {
    quizRepository.deleteAll();
    questionRepository.deleteAll();
  }

  @Test
  @Transactional
  public void shouldCorrectlySaveTest() {
    Quiz quiz = Quiz.builder()
            .title("test")
            .questions(List.of(generateQuestion(), generateQuestion(), generateQuestion()))
            .build();
    questionRepository.save(generateQuestion());
    quiz = quizRepository.save(quiz);

    Assert.assertEquals(4, questionRepository.count());
    Assert.assertEquals(3, quizQuestionRepository.count());
    Assert.assertEquals(3, quiz.getQuestions().size());
  }

  @Test
  public void shouldCorrectlyDeleteTest() {
    Quiz quiz = Quiz.builder()
            .title("test")
            .questions(List.of(generateQuestion(), generateQuestion(), generateQuestion()))
            .build();
    questionRepository.save(generateQuestion());
    quiz = quizRepository.save(quiz);

    quizRepository.delete(quiz);
    Assert.assertEquals(0, quizRepository.count());
    Assert.assertEquals(0, quizQuestionRepository.count());
    Assert.assertEquals(4, questionRepository.count());
  }

  private Question generateQuestion() {
    return Question.builder()
            .question("question")
            .type(QuestionType.ONE_ANSWER)
            .difficult(QuestionDifficult.MEDIUM)
            .build();
  }
}
