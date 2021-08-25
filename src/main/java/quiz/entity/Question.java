package quiz.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;


@Table(name = "question_t")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "quiz_kit_id")
    private UUID quizKitId;

    @Column(name = "question_no")
    private Integer questionNo;

}
