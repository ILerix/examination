package quiz.event;

import lombok.NoArgsConstructor;
import lombok.ToString;
import quiz.event.data.StartQuizData;


@ToString(callSuper = true)
@NoArgsConstructor
public class StartQuizEvent extends Event<StartQuizData> {

    public StartQuizEvent(Long chatId, StartQuizData data) {
        super(chatId, data);
    }
}
