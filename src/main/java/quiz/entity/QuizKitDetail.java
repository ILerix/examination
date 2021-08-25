package quiz.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;


@Table(name = "quiz_kit_detail_t")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuizKitDetail {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "quiz_kit_id")
    private UUID quizKitId;

    @Column(name = "question_id")
    private UUID questionId;

    @Column(name = "question_no")
    private Integer questionNo;
}
