package testing.core.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;


@Data
@Embeddable
public class QuizQuestionId implements Serializable {

  @Column(name = "test_id")
  private UUID quizId;

  @Column(name = "question_id")
  private UUID questionId;
}
