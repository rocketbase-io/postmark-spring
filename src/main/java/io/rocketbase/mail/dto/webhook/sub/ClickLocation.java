package io.rocketbase.mail.dto.webhook.sub;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum ClickLocation {

    HTML("HTML"),
    TEXT("Text");

    private String value;

    ClickLocation(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
