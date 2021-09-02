package quiz.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import quiz.entity.Session;
import quiz.repositories.SessionRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
public class SessionRepositoryJpaTest {

    @Autowired
    private SessionRepository sessionRepository;

    @Test
    public void shouldSuccessSaveSession() {
        Session session = sessionRepository.save(Session.builder().build());
        assertNotNull(session.getId());
    }
}
