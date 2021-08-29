package quiz.service.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import quiz.dto.PageInfoDto;
import quiz.dto.QuizKitInfo;
import quiz.event.PageControlEvent;
import quiz.service.QuizService;
import quiz.utils.KeyboardHelper;
import quiz.utils.MessageBuilder;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PageControlEventListener implements EventListener<PageControlEvent, PageInfoDto> {

    private final QuizService service;
    private final MessageBuilder messageBuilder;
    private final KeyboardHelper keyboardHelper;

    @Override
    public Class<PageControlEvent> getType() {
        return PageControlEvent.class;
    }

    @Override
    public PageInfoDto execute(PageControlEvent event) {
        List<QuizKitInfo> infos = service.movePage(event.getChatId(), event.getData().getIsNext());
        if (CollectionUtils.isEmpty(infos)) {
            return null;
        }

        PageInfoDto pageInfoDto = new PageInfoDto(infos);
        pageInfoDto.setChatId(event.getChatId());
        return pageInfoDto;
    }

    @Override
    public BotApiMethod<Message> toBotResponse(PageInfoDto response) {
        return SendMessage.builder()
                .chatId(response.getChatId().toString())
                .text(messageBuilder.buildPageQuizText(response.getQuizKits()))
                .replyMarkup(keyboardHelper.buildPageResponseKeyboard())
                .build();
    }

}
