package io.rocketbase.mail.dto.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
public class SpamComplaintWebhookMessage implements WebhookMessage{

    @JsonProperty("MessageID")
    private String messageId;

    @Override
    public WebhookRecordType getRecordType() {
        return WebhookRecordType.SPAM_COMPLAINT;
    }

    @JsonProperty("Email")
    private String email;

    @JsonProperty("Tag")
    private String tag;

    @JsonProperty("BouncedAt")
    @JsonDeserialize(using = PostmarkInstantDeserializer.class)
    private Instant bouncedAt;

    @JsonProperty("Subject")
    private String subject;

    @JsonProperty("Metadata")
    private Map<String, Object> metadata;

    @JsonProperty("Inactive")
    private boolean inactive;

    @JsonProperty("CanActivate")
    private boolean canActivate;
}
