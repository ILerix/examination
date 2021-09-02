package quiz.service.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import quiz.dto.PageInfoDto;
import quiz.dto.QuizKitInfo;
import quiz.event.PageStartViewEvent;
import quiz.service.impl.QuizKitServiceImpl;
import quiz.utils.KeyboardHelper;
import quiz.utils.MessageBuilder;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PageStartEventListener implements EventListener<PageStartViewEvent, PageInfoDto> {

    private final QuizKitServiceImpl quizService;
    private final KeyboardHelper keyboardHelper;
    private final MessageBuilder messageBuilder;

    @Override
    public Class<PageStartViewEvent> getType() {
        return PageStartViewEvent.class;
    }

    @Override
    public PageInfoDto execute(PageStartViewEvent event) {
        List<QuizKitInfo> infos = quizService.getPageQuizInfo(event.getChatId()).getContent();

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
