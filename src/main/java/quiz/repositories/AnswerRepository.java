package quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quiz.entity.Answer;
import quiz.entity.QuizKit;

import java.util.List;
import java.util.UUID;


@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID> {

    List<Answer> findAllByQuestionId(UUID questionId);

    Answer findByQuestionIdAndAnswerNo(UUID questionId, Integer answerNo);

}
