package testing.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import testing.core.entity.Quiz;
import testing.core.repository.QuizRepository;
import testing.core.service.QuizService;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

  private final QuizRepository repository;

  @Override
  public Quiz getById(UUID id) {
    return repository.findById(id)
            .orElseThrow(() -> { throw new RuntimeException("Quiz not found"); });
  }
}
