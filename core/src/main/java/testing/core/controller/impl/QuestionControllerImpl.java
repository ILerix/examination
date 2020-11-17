package testing.core.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rest.dto.QuestionCreate;
import rest.dto.QuestionDto;
import rest.dto.UserDto;
import testing.core.controller.QuestionController;
import testing.core.mapper.QuestionMapper;
import testing.core.service.QuestionService;

import java.util.UUID;


@Component
@RequiredArgsConstructor
public class QuestionControllerImpl implements QuestionController {

  private final QuestionService questionService;
  private final QuestionMapper questionMapper;

  @Override
  public QuestionDto getById(UUID id) {
    return questionMapper.toDto(questionService.getById(id));
  }

  @Override
  public QuestionDto create(QuestionCreate create) {
    return questionMapper.toDto(questionService.create(create));
  }
}
