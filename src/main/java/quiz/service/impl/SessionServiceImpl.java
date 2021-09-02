package quiz.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quiz.dto.ResultStatsDto;
import quiz.entity.Answer;
import quiz.entity.Question;
import quiz.entity.Session;
import quiz.repositories.SessionRepository;
import quiz.service.AnswerService;
import quiz.service.QuestionService;
import quiz.service.SessionService;

import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private final AnswerService answerService;
    private final QuestionService questionService;

    @Override
    public Optional<Session> findByChatId(Long chatId) {
        return sessionRepository.findByChatId(chatId);
    }

    @Override
    public Session startPageSession(Long chatId) {
        if (!sessionRepository.existsByChatId(chatId)) {
            return sessionRepository.save(Session.builder().chatId(chatId).currentPageNo(0).build());
        }
        return sessionRepository.findByChatId(chatId).get();
    }

    @Override
    public Session updateQuizPage(Long chatId, boolean next) {
        Session session = findByChatId(chatId).get();
        int newPageNo = next ? session.getCurrentPageNo() + 1 : session.getCurrentPageNo() - 1;
        if (newPageNo < 0 || newPageNo == sessionRepository.count()) {
            return null;
        }

        session.setCurrentPageNo(newPageNo);
        sessionRepository.save(session);

        return session;
    }

    @Override
    public ResultStatsDto getSessionStat(Long chatId) {
        return sessionRepository.findByChatId(chatId).map(session -> {
            return new ResultStatsDto(session.getCorrectAnswered(), session.getWrongAnswered());
        }).orElse(null);
    }

    @Override
    @Transactional
    public boolean toAnswer(Long chatId, Integer answerNo) {
        Session session = findByChatId(chatId).get();
        Question question = questionService
                .findByQuizKitIdAndQuestionNo(session.getQuizKitId(), session.getCurrentQuestion()).get();
        Answer answer = answerService.findByQuestionIdAndAnswerNo(question.getId(), answerNo);

        updateAfterAnswer(session, answer.getIsCorrect());

        return answer.getIsCorrect();
    }

    private void updateAfterAnswer(Session session, boolean isCorrectAnswer) {
        session.setCurrentQuestion(session.getCurrentQuestion() + 1);
        if (isCorrectAnswer) {
            session.setCorrectAnswered(session.getCorrectAnswered() + 1);
        } else {
            session.setWrongAnswered(session.getWrongAnswered() + 1);
        }
        sessionRepository.save(session);
    }

    @Override
    public void startQuizSession(Long chatId, UUID quizKitId) {
        Session session = Session.builder().chatId(chatId)
                .quizKitId(quizKitId)
                .currentQuestion(0)
                .correctAnswered(0)
                .wrongAnswered(0)
                .build();
        sessionRepository.save(session);
    }

    @Override
    @Transactional
    public void deleteSession(Long chatId) {
        sessionRepository.deleteByChatId(chatId);
    }
}
