package quiz.event.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StartQuizData {

    private Long chatId;

    private Integer quizKitNo;

}
