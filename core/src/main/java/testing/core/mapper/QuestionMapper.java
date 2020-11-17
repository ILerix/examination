package testing.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import rest.dto.QuestionCreate;
import rest.dto.QuestionDto;
import testing.core.entity.Question;


@Mapper(componentModel = "spring")
public interface QuestionMapper {

  QuestionDto toDto(Question entity);

  Question toEntity(QuestionDto dto);

  Question toEntity(QuestionCreate create);
}
