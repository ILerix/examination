package testing.core.api.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import rest.QuestionRestController;
import rest.dto.QuestionCreate;
import rest.dto.QuestionDto;
import testing.core.controller.QuestionController;

import java.util.UUID;


@Component
@RequiredArgsConstructor
public class QuestionRestControllerImpl implements QuestionRestController {

  private final QuestionController questionController;

  @Override
  public ResponseEntity<QuestionDto> get(UUID id) {
    return ResponseEntity.ok(questionController.getById(id));
  }

  @Override
  public ResponseEntity<QuestionDto> create(QuestionCreate create) {
    return ResponseEntity.ok(questionController.create(create));
  }
}
