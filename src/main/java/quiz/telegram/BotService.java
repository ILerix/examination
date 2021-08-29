package quiz.telegram;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import quiz.event.*;
import quiz.event.data.AnswerData;
import quiz.event.data.PageControlData;
import quiz.event.data.PageViewData;
import quiz.event.data.StartQuizData;
import quiz.event.EventDispatcher;
import quiz.service.type.Operation;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;


@Service
@RequiredArgsConstructor
public class BotService {

    private final EventDispatcher<Event<?>, ?> dispatcher;

    public Optional<BotApiMethod<Message>> processUpdate(Update update) {
        return Optional.ofNullable(dispatcher.execute(buildEvent(update)));
    }

    @SuppressWarnings("unchecked")
    private <T extends Event<?>> T buildEvent(Update update) {
        String text = update.getMessage().getText().toLowerCase();
        Long chatId = update.getMessage().getChatId();

        Optional<Operation> oper = Arrays.stream(Operation.values())
                .filter(operation -> isOperation(text, operation))
                .findAny();
        return oper.map(operation ->
                switch (operation) {
                    case PAGE_VIEW -> (T) new PageStartViewEvent(chatId, new PageViewData(chatId));
                    case NEXT_PAGE, PREVIOUS_PAGE ->
                            (T) new PageControlEvent(chatId, new PageControlData(text.startsWith("вперед")));
                    case ANSWER -> (T) new AnswerToQuestionEvent(chatId, new AnswerData(text));
                    case START_QUIZ -> (T) new StartQuizEvent(chatId, new StartQuizData(chatId, 1));
                })
                .orElse((T) new UnknownEvent(chatId, text));
    }

    private boolean isOperation(String text, Operation operation) {
        return text.startsWith(operation.getValue()) ||
                (operation == Operation.ANSWER
                        && Stream.of(operation.getValue().split("\\.")).anyMatch(text::startsWith));
    }

}
