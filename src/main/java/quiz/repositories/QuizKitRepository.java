package quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quiz.entity.QuizKit;

import java.util.UUID;


@Repository
public interface QuizKitRepository extends JpaRepository<QuizKit, UUID> {

    QuizKit findByKitNo(Integer kitNo);
}
