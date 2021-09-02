package quiz.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quiz.dto.AnswerDto;
import quiz.dto.QuestionToKeyboardDto;
import quiz.dto.QuizKitInfo;
import quiz.entity.Question;
import quiz.entity.QuizKit;
import quiz.entity.Session;
import quiz.repositories.QuizKitRepository;
import quiz.service.AnswerService;
import quiz.service.QuestionService;
import quiz.service.QuizKitService;
import quiz.service.SessionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class QuizKitServiceImpl implements QuizKitService {

    private final QuizKitRepository repository;

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final SessionService sessionService;

    @Override
    public Page<QuizKitInfo> getPageQuizInfo(Long chatId) {
        Session session = sessionService.startPageSession(chatId);
        PageRequest request = PageRequest.of(session.getCurrentPageNo(), 1);

        return repository.findAll(request)
                .map(kit -> new QuizKitInfo(kit.getDescription(), kit.getKitNo(), kit.getQuestionCount()));
    }

    @Override
    public List<QuizKitInfo> movePage(Long chatId, boolean isNext) {
        return Optional.ofNullable(sessionService.updateQuizPage(chatId, isNext)).map(session -> {
            PageRequest request = PageRequest.of(session.getCurrentPageNo(), 1);
            return repository.findAll(request)
                    .map(kit -> new QuizKitInfo(kit.getDescription(), kit.getKitNo(), kit.getQuestionCount()))
                    .getContent();
        }).orElse(null);
    }

    @Override
    @Transactional
    public void startQuiz(Long chatId, Integer kitNo) {
        sessionService.deleteSession(chatId);

        QuizKit kit = repository.findByKitNo(kitNo);
        sessionService.startQuizSession(chatId, kit.getId());
    }

    @Override
    @Transactional
    public QuestionToKeyboardDto getCurrentQuestion(Long chatId) {
        Session session = sessionService.findByChatId(chatId).get();
        QuizKit kit = repository.findById(session.getQuizKitId()).get();
        if (session.getCurrentQuestion().intValue() == kit.getQuestionCount().intValue()) {
            return null;
        }

        Question question = questionService
                .findByQuizKitIdAndQuestionNo(session.getQuizKitId(), session.getCurrentQuestion()).get();
        List<AnswerDto> answers = answerService.findAllByQuestionId(question.getId()).stream()
                .map(answer -> new AnswerDto(answer.getText(), answer.getIsCorrect()))
                .collect(Collectors.toList());

        return new QuestionToKeyboardDto(question.getDescription(), answers, null, false);
    }

}
