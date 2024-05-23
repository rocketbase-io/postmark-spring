package io.rocketbase.mail.dto.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.rocketbase.mail.dto.webhook.sub.SuppressionReason;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionChangeWebhookMessage implements WebhookMessage {

    @Override
    public WebhookRecordType getRecordType() {
        return WebhookRecordType.SUBSCRIPTION_CHANGE;
    }

    @JsonProperty("SuppressSending")
    private boolean suppressSending;

    @JsonProperty("SuppressionReason")
    private SuppressionReason suppressionReason;

    @JsonProperty("Origin")
    private String origin;

    @JsonProperty("ChangedAt")
    @JsonDeserialize(using = PostmarkInstantDeserializer.class)
    private Instant changedAt;

    @JsonProperty("Tag")
    private String tag;

    @JsonProperty("Metadata")
    private Map<String, Object> metadata;


}
