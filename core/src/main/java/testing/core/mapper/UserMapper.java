package testing.core.mapper;

import org.mapstruct.Mapper;
import rest.dto.UserDto;
import rest.dto.UserWithoutQuestion;
import testing.core.entity.User;


@Mapper(componentModel = "spring", uses = { QuestionMapper.class })
public interface UserMapper {

  UserDto toDto(User user);

  UserWithoutQuestion toDtoWithoutQuestion(User user);

  User toEntity(UserDto userDto);
}
