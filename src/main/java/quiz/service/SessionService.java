package quiz.service;

import quiz.dto.ResultStatsDto;
import quiz.entity.Session;

import java.util.Optional;
import java.util.UUID;

public interface SessionService {

    Optional<Session> findByChatId(Long chatId);

    Session startPageSession(Long chatId);

    Session updateQuizPage(Long chatId, boolean next);

    ResultStatsDto getSessionStat(Long chatId);

    boolean toAnswer(Long chatId, Integer answerNo);

    void startQuizSession(Long chatId, UUID quizKitId);

    void deleteSession(Long chatId);

}
