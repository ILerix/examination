package testing.core.service;


import rest.dto.QuestionCreate;
import rest.dto.QuestionDto;
import testing.core.entity.Question;

import java.util.UUID;


public interface QuestionService {

  Question getById(UUID id);

  Question create(QuestionCreate questionDto);
}
