package quiz.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quiz.entity.Question;
import quiz.repositories.QuestionRepository;
import quiz.service.QuestionService;

import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository repository;

    @Override
    public Optional<Question> findByQuizKitIdAndQuestionNo(UUID quizKitId, Integer questionNo) {
        return repository.findByQuizKitIdAndQuestionNo(quizKitId, questionNo);
    }
}
