package testing.core.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testing.core.entity.QuizQuestion;

import java.util.UUID;


@Repository
public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, UUID> {
}
