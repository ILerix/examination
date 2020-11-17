package testing.core.mapper;

import org.mapstruct.Mapper;
import rest.dto.QuizDto;
import rest.dto.UserDto;
import rest.dto.UserWithoutQuestion;
import testing.core.entity.Quiz;
import testing.core.entity.User;


@Mapper(componentModel = "spring", uses = { QuestionMapper.class })
public interface QuizMapper {

  QuizDto toDto(Quiz quiz);

}
