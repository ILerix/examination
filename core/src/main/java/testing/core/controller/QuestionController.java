package testing.core.controller;


import rest.dto.QuestionCreate;
import rest.dto.QuestionDto;
import rest.dto.UserDto;

import java.util.UUID;


public interface QuestionController {

  QuestionDto getById(UUID id);

  QuestionDto create(QuestionCreate create);

}
