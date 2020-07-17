package io.rocketbase.mail.dto.webhook;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryWebhookMessage implements WebhookMessage{

    @JsonProperty("MessageID")
    private String messageId;

    @Override
    public WebhookRecordType getRecordType() {
        return WebhookRecordType.DELIVERY;
    }

    @JsonProperty("Recipient")
    private String recipient;

    @JsonProperty("Tag")
    private String tag;

    @JsonProperty("DeliveredAt")
    @JsonDeserialize(using = PostmarkInstantDeserialzer.class)
    private Instant deliveredAt;

    @JsonProperty("Details")
    private String details;

    @JsonProperty("Metadata")
    private Map<String, Object> metadata;
}
