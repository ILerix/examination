package testing.core.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rest.dto.QuizDto;
import testing.core.controller.QuizController;
import testing.core.mapper.QuizMapper;
import testing.core.service.QuizService;

import java.util.UUID;


@Component
@RequiredArgsConstructor
public class QuizControllerImpl implements QuizController {

  private final QuizService quizService;
  private final QuizMapper quizMapper;

  @Override
  public QuizDto getById(UUID id) {
    return quizMapper.toDto(quizService.getById(id));
  }
}
