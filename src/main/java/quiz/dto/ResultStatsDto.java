package quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultStatsDto {

    private Integer correctAnswered;

    private Integer inCorrectAnswered;

}
