package quiz.service.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import quiz.dto.QuestionToKeyboardDto;
import quiz.event.data.StartQuizData;
import quiz.event.StartQuizEvent;
import quiz.utils.KeyboardHelper;
import quiz.service.impl.QuizKitServiceImpl;


@Service
@RequiredArgsConstructor
public class StartQuizEventListener implements EventListener<StartQuizEvent, QuestionToKeyboardDto> {

    private final QuizKitServiceImpl service;
    private final KeyboardHelper keyboardHelper;

    @Override
    public Class<StartQuizEvent> getType() {
        return StartQuizEvent.class;
    }

    @Override
    public QuestionToKeyboardDto execute(StartQuizEvent event) {
        StartQuizData data = event.getData();
        service.startQuiz(data.getChatId(), data.getQuizKitNo());

        QuestionToKeyboardDto dto = service.getCurrentQuestion(data.getChatId());
        dto.setChatId(event.getChatId());
        return dto;
    }

    @Override
    public BotApiMethod<Message> toBotResponse(QuestionToKeyboardDto response) {
        return SendMessage.builder()
                .chatId(response.getChatId().toString())
                .text(response.getText())
                .replyMarkup(keyboardHelper.buildResponseKeyboard(response.getAnswers()))
                .build();
    }

}
