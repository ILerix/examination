package quiz.service.type;

import lombok.Getter;

public enum Emoji {
    ERROR("\u274C"),
    GREEN_CHECK("\u2705");

    @Getter
    private final String code;

    Emoji(String code) { this.code = code; }
}
