package rest.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizDto {

  private UUID id;

  private String title;

  private ZonedDateTime createDate;

  private List<QuestionDto> questions;
}
