package quiz.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import quiz.service.consumer.EventListener;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EventDispatcher<T extends Event<?>, R extends Response> {

    private final List<EventListener<T, R>> listeners;

    public BotApiMethod<Message> execute(T event) {
        if (event == null) {
            return null;
        }

        return listeners.stream()
                .filter(eventListener -> eventListener.getType().equals(event.getClass()))
                .findAny()
                .map(listener -> {
                    R response = listener.execute(event);
                    return response == null ? null : listener.toBotResponse(response);
                }).orElse(null);
    }

}
