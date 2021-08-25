package quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quiz.entity.Question;
import quiz.entity.QuizKit;
import quiz.entity.QuizKitDetail;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {

    Optional<Question> findByQuizKitIdAndQuestionNo(UUID quizId, Integer questionNo);

}
