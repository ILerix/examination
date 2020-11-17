package testing.core.service;


import testing.core.entity.Quiz;
import testing.core.entity.User;

import java.util.UUID;


public interface QuizService {

  Quiz getById(UUID id);

}
