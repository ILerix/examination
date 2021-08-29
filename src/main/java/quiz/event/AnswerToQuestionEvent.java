package quiz.event;

import lombok.NoArgsConstructor;
import lombok.ToString;
import quiz.event.data.AnswerData;


@ToString(callSuper = true)
@NoArgsConstructor
public class AnswerToQuestionEvent extends Event<AnswerData> {

    public AnswerToQuestionEvent(Long chatId, AnswerData data) {
        super(chatId, data);
    }
}
