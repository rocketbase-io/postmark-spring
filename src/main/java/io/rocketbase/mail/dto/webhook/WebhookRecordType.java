package io.rocketbase.mail.dto.webhook;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum WebhookRecordType {

    DELIVERY("Delivery"),
    BOUNCE("Bounce"),
    SPAM_COMPLAINT("SpamComplaint"),
    OPEN("Open"),
    CLICK("Click"),
    SUBSCRIPTION_CHANGE("SubscriptionChange");

    @Getter
    @JsonValue
    private String value;

    WebhookRecordType(String value) {
        this.value = value;
    }

}
