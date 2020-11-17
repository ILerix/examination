package testing.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.dto.QuestionCreate;
import rest.dto.QuestionDto;
import testing.core.entity.Question;
import testing.core.entity.User;
import testing.core.exception.NotFoundException;
import testing.core.mapper.QuestionMapper;
import testing.core.repository.QuestionRepository;
import testing.core.service.QuestionService;
import testing.core.service.UserService;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

  private final QuestionRepository questionRepository;
  private final UserService userService;
  private final QuestionMapper questionMapper;

  @Override
  public Question getById(UUID id) {
    return questionRepository.findById(id)
            .orElseThrow(() -> { throw new NotFoundException("question not found"); });
  }

  @Override
  public Question create(QuestionCreate create) {
    User user = userService.getById(create.getUserId());
    Question question = questionMapper.toEntity(create);
    question.setUser(user);

    return questionRepository.save(question);
  }
}
