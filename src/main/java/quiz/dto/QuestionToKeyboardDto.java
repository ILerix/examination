package quiz.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import quiz.event.Response;

import java.util.List;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class QuestionToKeyboardDto extends Response {

    private String text;

    private List<AnswerDto> answers;

    private Boolean isCorrect;

    @Builder.Default
    private boolean isEnded = false;

}
