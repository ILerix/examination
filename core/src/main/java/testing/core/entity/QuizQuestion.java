package testing.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;


@Data
@Entity
@Table(name = "test_question_t")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuizQuestion {

  @EmbeddedId
  private QuizQuestionId id;

  @ManyToOne
  @MapsId("quizId")
  @JoinColumn(name = "test_id")
  private Quiz quiz;

  @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
  @MapsId("questionId")
  @JoinColumn(name = "question_id")
  private Question question;

}
