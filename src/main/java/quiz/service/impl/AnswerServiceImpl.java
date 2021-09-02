package quiz.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quiz.entity.Answer;
import quiz.repositories.AnswerRepository;
import quiz.service.AnswerService;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    @Override
    public Answer findByQuestionIdAndAnswerNo(UUID questionId, Integer answerNo) {
        return answerRepository.findByQuestionIdAndAnswerNo(questionId, answerNo);
    }

    @Override
    public List<Answer> findAllByQuestionId(UUID questionId) {
        return answerRepository.findAllByQuestionId(questionId);
    }
}
