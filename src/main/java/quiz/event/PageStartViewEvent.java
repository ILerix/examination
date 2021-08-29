package quiz.event;

import lombok.NoArgsConstructor;
import lombok.ToString;
import quiz.event.data.PageViewData;


@ToString(callSuper = true)
@NoArgsConstructor
public class PageStartViewEvent extends Event<PageViewData> {

    public PageStartViewEvent(Long chatId, PageViewData data) {
        super(chatId, data);
    }

}
