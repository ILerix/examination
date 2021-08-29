package quiz.service.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import quiz.dto.UnknownDto;
import quiz.event.UnknownEvent;


@Service
@Slf4j
public class UnknownMessageListener implements EventListener<UnknownEvent, UnknownDto> {

    @Override
    public Class<UnknownEvent> getType() {
        return UnknownEvent.class;
    }

    @Override
    public UnknownDto execute(UnknownEvent event) {
        log.warn("Received unknown: {}", event.getData());
        return UnknownDto.builder()
                .message("Unknown command received - " + event.getData())
                .chatId(event.getChatId())
                .build();
    }

    @Override
    public BotApiMethod<Message> toBotResponse(UnknownDto response) {
        return new SendMessage(response.getChatId().toString(), response.getMessage());
    }
}
