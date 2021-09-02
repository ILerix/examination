package quiz.utils;

import org.springframework.stereotype.Component;
import quiz.dto.QuizKitInfo;
import quiz.dto.ResultStatsDto;
import quiz.service.type.Emoji;

import java.util.List;


@Component
public class MessageBuilder {

    public String buildPageQuizText(List<QuizKitInfo> infos) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < infos.size(); i++) {
            result.append(i + 1).append(": ")
                    .append(infos.get(i).getDescription())
                    .append(", кол-во вопросов - ")
                    .append(infos.get(i).getQuestionCount())
                    .append(", номер теста - ")
                    .append(infos.get(i).getQuizNo())
                    .append("\n\n")
                    .append("Чтобы начать тест введите \"Тест\" <номер>, например: Тест 1");
        }
        return result.toString();
    }

    public String buildResultInfo(ResultStatsDto dto) {
        return "Тест окончен\n" +
                Emoji.GREEN_CHECK.getCode() +
                " Правильно отвечено: " +
                dto.getCorrectAnswered() + "\n" +
                Emoji.ERROR.getCode() +
                " Неправильно отвечено: " +
                dto.getInCorrectAnswered() + "\n";
    }

}
