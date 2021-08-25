package quiz.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;


@Table(name = "answer_t")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "question_id")
    private UUID questionId;

    @Column(name = "answer_no")
    private Integer answerNo;

    @Column(name = "answer_text")
    private String text;

    @Column(name = "is_correct")
    private Boolean isCorrect;

}
