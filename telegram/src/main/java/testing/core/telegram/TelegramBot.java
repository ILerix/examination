package testing.core.telegram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;


@Component
public class TelegramBot extends TelegramLongPollingBot {

  @Value("${telegram.token}")
  private String token;
  @Value("${telegram.name}")
  private String name;

  @Override
  public String getBotUsername() {
    return name;
  }

  @Override
  public String getBotToken() {
    return token;
  }

  @Override
  public void onUpdateReceived(Update update) {
    System.out.println(update.getMessage().getText());
    try {
      String response = update.getMessage().getText();

      this.execute(new SendMessage(update.getMessage().getChat().getId().toString(),
              String.format("Your response is \"%s\"", response)));
      this.execute(new SendMessage(update.getMessage().getChat().getId().toString(),
              "\uD83D\uDC4D"));
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }

  }
}
