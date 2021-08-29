package quiz.service.type;

import lombok.Getter;


public enum Operation {
    PAGE_VIEW("начать"),
    NEXT_PAGE("вперед"),
    PREVIOUS_PAGE("назад"),
    ANSWER("1.2.3.4."),
    START_QUIZ("тест");

    @Getter
    private final String value;

    Operation(String value) {
        this.value = value;
    }
}
