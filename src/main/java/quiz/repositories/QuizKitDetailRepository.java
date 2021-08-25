package quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quiz.entity.QuizKit;
import quiz.entity.QuizKitDetail;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface QuizKitDetailRepository extends JpaRepository<QuizKitDetail, UUID> {

    Optional<QuizKitDetail> findByQuizKitIdAndQuestionNo(UUID quizId, Integer questionNo);

}
