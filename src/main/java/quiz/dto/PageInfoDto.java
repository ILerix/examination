package quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import quiz.event.Response;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageInfoDto extends Response {

    private List<QuizKitInfo> quizKits;

}
