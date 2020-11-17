package testing.core.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import testing.core.entity.types.QuestionDifficult;
import testing.core.entity.types.QuestionType;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@Builder
@Table(name = "question_t")
@AllArgsConstructor
@NoArgsConstructor
public class Question {

  @Id
  @GeneratedValue
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "author_id")
  private User user;

  @ManyToMany(mappedBy = "questions")
  private List<Quiz> quizzes;

//  @OneToMany(mappedBy = "question", cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
//  private List<QuizQuestion> quizQuestions;

  private String question;

  @Enumerated(EnumType.STRING)
  private QuestionType type;

  @Enumerated(EnumType.STRING)
  private QuestionDifficult difficult;

}
