package testing.core.api.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import rest.UserRestController;
import rest.dto.UserDto;
import testing.core.controller.UserController;

import java.util.UUID;


@Component
@RequestMapping
@RequiredArgsConstructor
public class UserRestControllerImpl implements UserRestController {

  private final UserController userController;

  @Override
  public ResponseEntity<UserDto> get(UUID id) {
    return ResponseEntity.ok(userController.getById(id));
  }

  @Override
  public ResponseEntity<UserDto> create(UserDto user) {
    return ResponseEntity.ok(userController.create(user));
  }

  @Override
  public ResponseEntity<UserDto> update(UserDto user) {
    return null;
  }
}
