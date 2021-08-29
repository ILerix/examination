package quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import quiz.event.Response;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UnknownDto extends Response {

    private String message;
}
