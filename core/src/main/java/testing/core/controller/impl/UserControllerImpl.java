package testing.core.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rest.dto.UserDto;
import testing.core.controller.UserController;
import testing.core.mapper.UserMapper;
import testing.core.service.UserService;

import java.util.UUID;


@Component
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  @Override
  public UserDto getById(UUID id) {
    return userMapper.toDto(userService.getById(id));
  }

  @Override
  public UserDto create(UserDto userDto) {
    return userMapper.toDto(userService.save(userMapper.toEntity(userDto)));
  }
}
