package quiz.telegram;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import quiz.dto.AnswerDto;
import quiz.dto.QuestionToKeyboardDto;
import quiz.dto.QuizKitInfo;
import quiz.dto.ResultStatsDto;
import quiz.service.QuizService;

import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot {

    private final QuizService service;

    @Value("${telegram.name}")
    private String name;
    @Value("${telegram.token}")
    private String token;

    private static final List<String> ANSWER_NUMBERS = List.of("1.", "2.", "3.", "4.");
    private static final List<String> PAGE_CONTROL = List.of("Вперед", "Назад");


    @Override
    public void onUpdateReceived(Update update) {
        try {
            String message = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            if (message.contains("Начать")) {
                Page<QuizKitInfo> infos = service.getPageQuizInfo(chatId);
                execute(SendMessage.builder()
                        .chatId(chatId.toString())
                        .text(buildPageQuiz(infos))
                        .replyMarkup(buildPageResponseKeyboard())
                        .build());
                return;
            }
            if (message.contains("Тест")) {
                int quizNo = Integer.parseInt(message.substring("Тест ".length()));
                service.startQuiz(chatId, quizNo);
                QuestionToKeyboardDto dto = service.getCurrentQuestion(chatId);

                sendQuestion(chatId, dto);
                return;
            }
            if (ANSWER_NUMBERS.contains(message.substring(0, 2))) {
                boolean isCorrect = service.toAnswer(chatId, Integer.valueOf(message.substring(0, 1)));
                QuestionToKeyboardDto dto = service.getCurrentQuestion(chatId);

                execute(new SendMessage(chatId.toString(),
                        isCorrect ? "Правильно \u2705" : "Неправильно \u274C"));
                if (dto == null) {
                    ResultStatsDto resultStatsDto = service.getResultInfo(chatId);
                    execute(SendMessage.builder()
                            .chatId(chatId.toString())
                            .text(buildResultInfo(resultStatsDto))
                            .replyMarkup(new ReplyKeyboardRemove(true))
                            .build());
                    service.endSession(chatId);
                    return;
                }
                sendQuestion(chatId, dto);
                return;
            }
            if (PAGE_CONTROL.contains(message)) {
                boolean isNext = message.equals("Вперед");
                Page<QuizKitInfo> infos = service.movePage(chatId, isNext);
                if (infos == null) {
                    return;
                }
                execute(SendMessage.builder()
                        .chatId(chatId.toString())
                        .text(buildPageQuiz(infos))
                        .replyMarkup(buildPageResponseKeyboard())
                        .build());
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendQuestion(Long chatId, QuestionToKeyboardDto dto) throws TelegramApiException {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId.toString())
                .text(dto.getText())
                .replyMarkup(buildResponseKeyboard(dto.getAnswers()))
                .build();
        execute(sendMessage);
    }

    private String buildPageQuiz(Page<QuizKitInfo> infos) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < infos.getContent().size(); i++) {
            result.append(i + 1).append(": ")
                    .append(infos.getContent().get(i).getDescription())
                    .append(", кол-во вопросов - ")
                    .append(infos.getContent().get(i).getQuestionCount())
                    .append(", номер теста - ")
                    .append(infos.getContent().get(i).getQuizNo())
                    .append("\n\n")
                    .append("Чтобы начать тест введите \"Тест\" <номер>, например: Тест 1");
        }
        return result.toString();
    }

    private String buildResultInfo(ResultStatsDto dto) {
        StringBuilder result = new StringBuilder();
        return result.append("Тест окончен\n")
                .append("\u2705 Правильно отвечено: ")
                .append(dto.getCorrectAnswered()).append("\n")
                .append("\u274C Неправильно отвечено: ")
                .append(dto.getInCorrectAnswered()).append("\n")
                .toString();
    }

    private ReplyKeyboardMarkup buildPageResponseKeyboard() {
        List<KeyboardRow> buttonRows = new ArrayList<>();
        buttonRows.add(new KeyboardRow());
        buttonRows.get(0).add("Назад");
        buttonRows.get(0).add("Вперед");

        return new ReplyKeyboardMarkup(buttonRows);
    }


    private ReplyKeyboardMarkup buildResponseKeyboard(List<AnswerDto> answers) {
        List<KeyboardRow> buttonRows = new ArrayList<>();
        buttonRows.add(new KeyboardRow());
        buttonRows.get(0).add("1. " + answers.get(0).getText());
        buttonRows.get(0).add("2. " + answers.get(1).getText());

        return new ReplyKeyboardMarkup(buttonRows);
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
