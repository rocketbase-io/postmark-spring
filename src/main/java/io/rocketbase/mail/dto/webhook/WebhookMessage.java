package io.rocketbase.mail.dto.webhook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "RecordType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DeliveryWebhookMessage.class, name = "Delivery"),
        @JsonSubTypes.Type(value = BounceWebhookMessage.class, name = "Bounce"),
        @JsonSubTypes.Type(value = SpamComplaintWebhookMessage.class, name = "SpamComplaint"),
        @JsonSubTypes.Type(value = OpenWebhookMessage.class, name = "Open"),
        @JsonSubTypes.Type(value = ClickWebhookMessage.class, name = "Click"),
        @JsonSubTypes.Type(value = SubscriptionChangeWebhookMessage.class, name = "SubscriptionChange"),
})
@JsonIgnoreProperties(ignoreUnknown = true)
public interface WebhookMessage {

    WebhookRecordType getRecordType();
}
