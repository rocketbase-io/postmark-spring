package io.rocketbase.mail.dto.webhook;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.rocketbase.mail.dto.webhook.sub.ClickLocation;
import io.rocketbase.mail.dto.webhook.sub.Client;
import io.rocketbase.mail.dto.webhook.sub.OperationSystem;
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
public class ClickWebhookMessage implements WebhookMessage{

    @JsonProperty("MessageID")
    private String messageId;

    @Override
    public WebhookRecordType getRecordType() {
        return WebhookRecordType.CLICK;
    }

    @JsonProperty("Recipient")
    private String recipient;

    @JsonProperty("Tag")
    private String tag;

    @JsonProperty("Metadata")
    private Map<String, Object> metadata;

    @JsonProperty("OriginalLink")
    private String originalLink;

    @JsonProperty("ClickLocation")
    private ClickLocation clickLocation;

    @JsonProperty("ReceivedAt")
    @JsonDeserialize(using = PostmarkInstantDeserialzer.class)
    private Instant receivedAt;

    @JsonProperty("Client")
    private Client client;

    @JsonProperty("OS")
    private OperationSystem os;

    @JsonProperty("UserAgent")
    private String userAgent;
}
