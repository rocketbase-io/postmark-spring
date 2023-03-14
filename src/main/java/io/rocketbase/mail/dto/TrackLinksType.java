package io.rocketbase.mail.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum TrackLinksType {

    NONE("None"),
    HTML_ONLY("HtmlOnly"),
    HTML_AND_TEXT("HtmlAndText"),
    TEXT_ONLY("TextOnly");

    private String value;

    TrackLinksType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }
}
