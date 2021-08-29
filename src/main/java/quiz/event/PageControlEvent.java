package quiz.event;

import lombok.NoArgsConstructor;
import lombok.ToString;
import quiz.event.data.PageControlData;


@ToString(callSuper = true)
@NoArgsConstructor
public class PageControlEvent extends Event<PageControlData> {

    public PageControlEvent(Long chatId, PageControlData data) {
        super(chatId, data);
    }

}
