package quiz.utils;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import quiz.dto.AnswerDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static quiz.config.TextConstants.NEXT_PAGE;
import static quiz.config.TextConstants.PREVIOUS_PAGE;


@Component
public class KeyboardHelper {

    public ReplyKeyboardMarkup buildResponseKeyboard(List<AnswerDto> answers) {
        int rows = answers.size() % 2 == 0 ? answers.size() / 2 : answers.size() / 2 + 1;

        List<KeyboardRow> buttonRows = new ArrayList<>();
        IntStream.range(0, rows).forEach(row -> {
            buttonRows.add(new KeyboardRow());
            buttonRows.get(buttonRows.size() - 1).add((row * 2 + 1) + ". " + answers.get(row * 2).getText());
            buttonRows.get(buttonRows.size() - 1).add((row * 2 + 2) + ". " + answers.get(row * 2 + 1).getText());
        });
        return new ReplyKeyboardMarkup(buttonRows);
    }

    public ReplyKeyboardMarkup buildPageResponseKeyboard() {
        List<KeyboardRow> buttonRows = new ArrayList<>();
        buttonRows.add(new KeyboardRow());
        buttonRows.get(0).add(PREVIOUS_PAGE);
        buttonRows.get(0).add(NEXT_PAGE);

        return new ReplyKeyboardMarkup(buttonRows);
    }
}
