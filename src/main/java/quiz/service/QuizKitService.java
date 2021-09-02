package quiz.service;

import org.springframework.data.domain.Page;
import quiz.dto.QuestionToKeyboardDto;
import quiz.dto.QuizKitInfo;

import java.util.List;

public interface QuizKitService {

    Page<QuizKitInfo> getPageQuizInfo(Long chatId);

    List<QuizKitInfo> movePage(Long chatId, boolean isNext);

    void startQuiz(Long chatId, Integer kitNo);

    QuestionToKeyboardDto getCurrentQuestion(Long chatId);
}
