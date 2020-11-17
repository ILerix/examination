package rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionCreate {

  private UUID userId;

  private String question;

  private QuestionType type;

  private QuestionDifficult difficult;

}
