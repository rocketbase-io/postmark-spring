package io.rocketbase.mail.dto.webhook.sub;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum BounceType {

    /**
     * Hard bounce — The server was unable to deliver your message (ex: unknown user, mailbox not found).
     */
    HARD_BOUNCE("HardBounce", 1),
    /**
     * Message delayed — The server could not temporarily deliver your message (ex: Message is delayed due to network troubles).
     */
    TRANSIENT("Transient", 2),
    /**
     * Unsubscribe request — Unsubscribe or Remove request.
     */
    UNSUBSCRIBE("Unsubscribe", 16),
    /**
     * Subscribe request — Subscribe request from someone wanting to get added to the mailing list.
     */
    SUBSCRIBE("Subscribe", 32),
    /**
     * Auto responder — Automatic email responder (ex: "Out of Office" or "On Vacation").
     */
    AUTO_RESPONDER("AutoResponder", 64),
    /**
     * Address change — The recipient has requested an address change.
     */
    ADDRESS_CHANGE("AddressChange", 128),
    /**
     * DNS error — A temporary DNS error.
     */
    DNS_ERROR("DnsError", 256),
    /**
     * Spam notification — The message was delivered, but was either blocked by the user, or classified as spam, bulk mail, or had rejected content.
     */
    SPAM_NOTIFICATION("SpamNotification", 512),
    /**
     * Open relay test — The NDR is actually a test email message to see if the mail server is an open relay.
     */
    OPEN_RELAY_TEST("OpenRelayTest", 1024),
    /**
     * Unknown — Unable to classify the NDR.
     */
    UNKNOWN("Unknown", 2048),
    /**
     * Soft bounce — Unable to temporarily deliver message (i.e. mailbox full, account disabled, exceeds quota, out of disk space).
     */
    SOFTBOUNCE("SoftBounce", 4096),
    /**
     * Virus notification — The bounce is actually a virus notification warning about a virus/code infected message.
     */
    VIRUS_NOTIFICATION("VirusNotification", 8192),
    /**
     * Spam challenge verification — The bounce is a challenge asking for verification you actually sent the email. Typcial challenges are made by Spam Arrest, or MailFrontier Matador.
     */
    CHALLENGE_VERIFICATION("ChallengeVerification", 16384),
    /**
     * Invalid email address — The address is not a valid email address.
     */
    BAD_EMAIL_ADDRESS("BadEmailAddress", 100000),
    /**
     * Spam complaint — The subscriber explicitly marked this message as spam.
     */
    SPAM_COMPLAINT("SpamComplaint", 100001),
    /**
     * Manually deactivated — The email was manually deactivated.
     */
    MANUALLY_DEACTIVATED("ManuallyDeactivated", 100002),
    /**
     * Registration not confirmed — The subscriber has not clicked on the confirmation link upon registration or import.
     */
    UNCONFIRMED("Unconfirmed", 100003),
    /**
     * ISP block — Blocked from this ISP due to content or blacklisting.
     */
    BLOCKED("Blocked", 100006),
    /**
     * SMTP API error — An error occurred while accepting an email through the SMTP API.
     */
    SMTP_API_ERROR("SMTPApiError", 100007),
    /**
     * Processing failed — Unable to deliver inbound message to destination inbound hook.
     */
    INBOUND_ERROR("InboundError", 100008),
    /**
     * DMARC Policy — Email rejected due DMARC Policy.
     */
    DMARC_POLICY("DMARCPolicy", 100009),
    /**
     * Template rendering failed — An error occurred while attempting to render your template.
     */
    TEMPLATE_RENDERING_FAILED("TemplateRenderingFailed", 100010);

    private String value;

    @Getter
    private int code;

    BounceType(String value, int code) {
        this.value = value;
        this.code = code;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
