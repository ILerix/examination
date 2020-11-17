package testing.core.controller;


import rest.dto.QuizDto;
import rest.dto.UserDto;

import java.util.UUID;


public interface QuizController {

  QuizDto getById(UUID id);

}
