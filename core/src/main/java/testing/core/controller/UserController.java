package testing.core.controller;


import rest.dto.UserDto;

import java.util.UUID;


public interface UserController {

  UserDto getById(UUID id);

  UserDto create(UserDto userDto);

}
