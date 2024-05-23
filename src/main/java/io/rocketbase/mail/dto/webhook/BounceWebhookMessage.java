package io.rocketbase.mail.dto.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.rocketbase.mail.dto.webhook.sub.BounceType;
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
public class BounceWebhookMessage implements WebhookMessage {

    @JsonProperty("MessageID")
    private String messageId;

    @Override
    public WebhookRecordType getRecordType() {
        return WebhookRecordType.BOUNCE;
    }

    @JsonProperty("ID")
    private Long id;

    @JsonProperty("Type")
    private BounceType type;

    @JsonProperty("TypeCode")
    private Integer typeCode;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Email")
    private String email;

    @JsonProperty("From")
    private String from;

    @JsonProperty("Metadata")
    private Map<String, Object> metadata;

    @JsonProperty("Inactive")
    private boolean inactive;

    @JsonProperty("CanActivate")
    private boolean canActivate;

    @JsonProperty("BouncedAt")
    @JsonDeserialize(using = PostmarkInstantDeserializer.class)
    private Instant bouncedAt;


}
