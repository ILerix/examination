package quiz.service.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import quiz.dto.QuestionToKeyboardDto;
import quiz.dto.ResultStatsDto;
import quiz.event.AnswerToQuestionEvent;
import quiz.utils.KeyboardHelper;
import quiz.service.QuizService;
import quiz.utils.MessageBuilder;


@Service
@RequiredArgsConstructor
public class AnswerEventListener implements EventListener<AnswerToQuestionEvent, QuestionToKeyboardDto> {

    private final QuizService service;
    private final MessageBuilder messageBuilder;
    private final KeyboardHelper keyboardHelper;

    @Override
    public Class<AnswerToQuestionEvent> getType() {
        return AnswerToQuestionEvent.class;
    }

    @Override
    public QuestionToKeyboardDto execute(AnswerToQuestionEvent event) {
        int answerNo = Integer.parseInt(event.getData().getAnswer().substring(0, 1));

        boolean isCorrect = service.toAnswer(event.getChatId(), answerNo);
        QuestionToKeyboardDto dto = service.getCurrentQuestion(event.getChatId());
        if (dto == null) {
            return QuestionToKeyboardDto.builder().chatId(event.getChatId()).isCorrect(isCorrect).isEnded(true).build();
        }

        dto.setChatId(event.getChatId());
        dto.setIsCorrect(isCorrect);
        return dto;
    }

    @Override
    public BotApiMethod<Message> toBotResponse(QuestionToKeyboardDto response) {
        String correctText = response.getIsCorrect() ? "Правильно \u2705 \n\n" : "Неправильно \u274C \n\n";
        if (response.isEnded()) {
            ResultStatsDto resultStatsDto = service.getResultInfo(response.getChatId());
            service.endSession(response.getChatId());

            return SendMessage.builder()
                    .chatId(response.getChatId().toString())
                    .text(correctText + messageBuilder.buildResultInfo(resultStatsDto))
                    .replyMarkup(new ReplyKeyboardRemove(true))
                    .build();
        }

        return SendMessage.builder()
                .chatId(response.getChatId().toString())
                .text(correctText + response.getText())
                .replyMarkup(keyboardHelper.buildResponseKeyboard(response.getAnswers()))
                .build();
    }

}
