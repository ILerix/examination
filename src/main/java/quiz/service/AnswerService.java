package quiz.service;

import quiz.entity.Answer;

import java.util.List;
import java.util.UUID;

public interface AnswerService {

    Answer findByQuestionIdAndAnswerNo(UUID questionId, Integer answerNo);

    List<Answer> findAllByQuestionId(UUID questionId);
}
