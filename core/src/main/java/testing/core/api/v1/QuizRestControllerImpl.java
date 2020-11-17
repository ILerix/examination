package testing.core.api.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import rest.QuizRestController;
import rest.dto.QuizDto;
import rest.dto.UserDto;
import testing.core.controller.QuizController;

import java.util.UUID;


@Component
@RequiredArgsConstructor
public class QuizRestControllerImpl implements QuizRestController {

  private final QuizController quizController;

  @Override
  public ResponseEntity<QuizDto> get(UUID id) {
    return ResponseEntity.ok(quizController.getById(id));
  }
}
