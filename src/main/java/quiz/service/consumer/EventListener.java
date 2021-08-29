package quiz.service.consumer;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import quiz.event.Response;
import quiz.event.Event;


public interface EventListener<T extends Event<?>, R extends Response> {

    Class<T> getType();

    R execute(T event);

    BotApiMethod<Message> toBotResponse(R response);
}
