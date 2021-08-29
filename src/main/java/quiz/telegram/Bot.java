package quiz.telegram;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot {

    private final BotService botService;

    @Value("${telegram.name}")
    private String name;
    @Value("${telegram.token}")
    private String token;

    @Override
    public void onUpdateReceived(Update update) {
        botService.processUpdate(update).ifPresent(this::send);
    }

    private void send(BotApiMethod<Message> method) {
        try {
            execute(method);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
