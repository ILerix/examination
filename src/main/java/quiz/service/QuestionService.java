package quiz.service;

import quiz.entity.Question;

import java.util.Optional;
import java.util.UUID;

public interface QuestionService {

    Optional<Question> findByQuizKitIdAndQuestionNo(UUID quizKitId, Integer questionNo);

}
