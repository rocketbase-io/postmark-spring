package io.rocketbase.mail.dto.webhook.sub;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum SuppressionReason {
    HARD_BOUNCE("HardBounce"),
    SPAM_COMPLAINT("SpamComplaint"),
    MANUAL_SUPPRESSION("ManualSuppression");

    private String value;

    SuppressionReason(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

}
