package quiz.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;


@Table(name = "quiz_kit_t")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuizKit {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "question_count")
    private Integer questionCount;

    @Column(name = "kit_no")
    private Integer kitNo;

}


