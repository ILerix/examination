package quiz.event;

import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString(callSuper = true)
@NoArgsConstructor
public class UnknownEvent extends Event<String> {

    public UnknownEvent(Long chatId, String data) {
        super(chatId, data);
    }
}
