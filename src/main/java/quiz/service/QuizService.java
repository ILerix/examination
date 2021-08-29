package quiz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quiz.dto.AnswerDto;
import quiz.dto.QuestionToKeyboardDto;
import quiz.dto.QuizKitInfo;
import quiz.dto.ResultStatsDto;
import quiz.entity.Answer;
import quiz.entity.Question;
import quiz.entity.QuizKit;
import quiz.entity.Session;
import quiz.repositories.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizKitRepository repository;
    private final QuizKitDetailRepository detailRepository;
    private final SessionRepository sessionRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @PostConstruct
    public void initTestData() {
        QuizKit kit1 = QuizKit.builder().kitNo(1).description("test 1").questionCount(2).build();
        QuizKit kit2 = QuizKit.builder().kitNo(2).description("test 2").questionCount(1).build();
        repository.save(kit1);
        repository.save(kit2);

        Question question1 = Question.builder().description("question 1").questionNo(0).quizKitId(kit1.getId()).build();
        Question question2 = Question.builder().description("question 2").questionNo(1).quizKitId(kit1.getId()).build();
        Question question3 = Question.builder().description("question 3").questionNo(0).quizKitId(kit2.getId()).build();

        questionRepository.save(question1);
        questionRepository.save(question2);
        questionRepository.save(question3);

        Answer answer1 = Answer.builder().text("ans1").answerNo(1).questionId(question1.getId()).isCorrect(false).build();
        Answer answer2 = Answer.builder().text("ans2").answerNo(2).questionId(question1.getId()).isCorrect(true).build();
        Answer answer3 = Answer.builder().text("ans3").answerNo(1).questionId(question2.getId()).isCorrect(false).build();
        Answer answer4 = Answer.builder().text("ans4").answerNo(2).questionId(question2.getId()).isCorrect(true).build();
        Answer answer5 = Answer.builder().text("ans5").answerNo(1).questionId(question3.getId()).isCorrect(false).build();
        Answer answer6 = Answer.builder().text("ans6").answerNo(2).questionId(question3.getId()).isCorrect(true).build();

        answerRepository.save(answer1);
        answerRepository.save(answer2);
        answerRepository.save(answer3);
        answerRepository.save(answer4);
        answerRepository.save(answer5);
        answerRepository.save(answer6);
    }

    public ResultStatsDto getResultInfo(Long chatId) {
        Session session = sessionRepository.findByChatId(chatId).get();

        return new ResultStatsDto(session.getCorrectAnswered(), session.getInCorrectAnswered());
    }

    @Transactional
    public Page<QuizKitInfo> getPageQuizInfo(Long chatId) {
        Session session;
        if (!sessionRepository.existsByChatId(chatId)) {
            session = sessionRepository.save(Session.builder().chatId(chatId).currentPageNo(0).build());
        } else {
            session = sessionRepository.findByChatId(chatId).get();
        }

        PageRequest request = PageRequest.of(session.getCurrentPageNo(), 1);
        return repository.findAll(request)
                .map(kit -> new QuizKitInfo(kit.getDescription(), kit.getKitNo(), kit.getQuestionCount()));
    }

    public List<QuizKitInfo> movePage(Long chatId, boolean isNext) {
        Session session = sessionRepository.findByChatId(chatId).get();
        int newPageNo = isNext ? session.getCurrentPageNo() + 1 : session.getCurrentPageNo() - 1;

        if (newPageNo < 0 || newPageNo == repository.count()) {
            return null;
        }

        PageRequest request = PageRequest.of(newPageNo, 1);
        Page<QuizKitInfo> page = repository.findAll(request)
                .map(kit -> new QuizKitInfo(kit.getDescription(), kit.getKitNo(), kit.getQuestionCount()));

        session.setCurrentPageNo(newPageNo);
        sessionRepository.save(session);
        return page.getContent();
    }

    @Transactional
    public void startQuiz(Long chatId, Integer kitNo) {
        sessionRepository.deleteByChatId(chatId);

        QuizKit kit = repository.findByKitNo(kitNo);
        Session session = Session.builder().chatId(chatId).quizKitId(kit.getId())
                .currentQuestion(0)
                .correctAnswered(0)
                .inCorrectAnswered(0)
                .build();
        sessionRepository.save(session);
    }

    @Transactional
    public QuestionToKeyboardDto getCurrentQuestion(Long chatId) {
        Session session = sessionRepository.findByChatId(chatId).get();
        QuizKit kit = repository.findById(session.getQuizKitId()).get();
        if (session.getCurrentQuestion().intValue() == kit.getQuestionCount().intValue()) {
            return null;
        }

        Question question = questionRepository
                .findByQuizKitIdAndQuestionNo(session.getQuizKitId(), session.getCurrentQuestion()).get();
        List<AnswerDto> answers = answerRepository.findAllByQuestionId(question.getId()).stream()
                .map(answer -> new AnswerDto(answer.getText(), answer.getIsCorrect()))
                .collect(Collectors.toList());

        return new QuestionToKeyboardDto(question.getDescription(), answers, null, false);
    }

    @Transactional
    public boolean toAnswer(Long chatId, Integer answerNo) {
        Session session = sessionRepository.findByChatId(chatId).get();
        Question question = questionRepository
                .findByQuizKitIdAndQuestionNo(session.getQuizKitId(), session.getCurrentQuestion()).get();
        Answer answer = answerRepository.findByQuestionIdAndAnswerNo(question.getId(), answerNo);

        session.setCurrentQuestion(session.getCurrentQuestion() + 1);
        if (answer.getIsCorrect()) {
            session.setCorrectAnswered(session.getCorrectAnswered() + 1);
        } else {
            session.setInCorrectAnswered(session.getInCorrectAnswered() + 1);
        }
        sessionRepository.save(session);

        return answer.getIsCorrect();
    }

    @Transactional
    public void endSession(Long chatId) {
        sessionRepository.deleteByChatId(chatId);
    }
}
