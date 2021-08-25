package quiz.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;


@Table(name = "session_t")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Session {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "quiz_kit_id")
    private UUID quizKitId;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "current_page_no")
    private Integer currentPageNo;

    @Column(name = "current_question_no")
    private Integer currentQuestion;

    @Column(name = "correct_answered")
    private Integer correctAnswered;

    @Column(name = "incorrect_answered")
    private Integer inCorrectAnswered;

    @Column(name = "is_random_order")
    private Boolean isRandomOrder;
}


