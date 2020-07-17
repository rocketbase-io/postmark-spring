package io.rocketbase.mail.dto.webhook;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.rocketbase.mail.dto.webhook.sub.Client;
import io.rocketbase.mail.dto.webhook.sub.Geo;
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
public class OpenWebhookMessage implements WebhookMessage{

    @JsonProperty("MessageID")
    private String messageId;

    @Override
    public WebhookRecordType getRecordType() {
        return WebhookRecordType.OPEN;
    }

    @JsonProperty("Recipient")
    private String recipient;

    @JsonProperty("FirstOpen")
    private boolean firstOpen;

    @JsonProperty("Geo")
    private Geo geo;

    @JsonProperty("Metadata")
    private Map<String, Object> metadata;

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
