package quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import quiz.entity.QuizKit;
import quiz.entity.Session;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {

    Optional<Session> findByChatId(Long chatId);

    boolean existsByChatId(Long chatId);

    void deleteByChatId(Long chatId);

}
