package io.rocketbase.mail.dto.webhook;

import com.fasterxml.jackson.annotation.JsonValue;

public enum WebhookRecordType {

    DELIVERY("Delivery"),
    BOUNCE("Bounce"),
    SPAM_COMPLAINT("SpamComplaint"),
    OPEN("Open"),
    CLICK("Click"),
    SUBSCRIPTION_CHANGE("SubscriptionChange");


    private String value;

    WebhookRecordType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }
}
