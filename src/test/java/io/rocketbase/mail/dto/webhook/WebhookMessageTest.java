package io.rocketbase.mail.dto.webhook;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class WebhookMessageTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testBounce() throws Exception {
        // given
        URL json = ClassLoader.class.getResource("/webhook/bounce.json");

        // when
        WebhookMessage message = objectMapper.readValue(json, WebhookMessage.class);

        // then
        assertThat(message, instanceOf(BounceWebhookMessage.class));
        assertThat(message.getRecordType(), equalTo(WebhookRecordType.BOUNCE));

        BounceWebhookMessage bounceMessage = (BounceWebhookMessage) message;
        assertThat(bounceMessage.getMessageId(), equalTo("883953f4-6105-42a2-a16a-77a8eac79483"));
        assertThat(bounceMessage.getBouncedAt(), notNullValue());
        assertThat(bounceMessage.getEmail(), notNullValue());
        assertThat(bounceMessage.getFrom(), notNullValue());
        assertThat(bounceMessage.getId(), notNullValue());
        assertThat(bounceMessage.getName(), notNullValue());
        assertThat(bounceMessage.getType(), notNullValue());
        assertThat(bounceMessage.getTypeCode(), notNullValue());
    }

    @Test
    public void testClick() throws Exception {
        // given
        URL json = ClassLoader.class.getResource("/webhook/click.json");

        // when
        WebhookMessage message = objectMapper.readValue(json, WebhookMessage.class);

        // then
        assertThat(message, instanceOf(ClickWebhookMessage.class));
        assertThat(message.getRecordType(), equalTo(WebhookRecordType.CLICK));

        ClickWebhookMessage clickMessage = (ClickWebhookMessage) message;
        assertThat(clickMessage.getMessageId(), equalTo("00000000-0000-0000-0000-000000000000"));
        assertThat(clickMessage.getClickLocation(), notNullValue());
        assertThat(clickMessage.getClient(), notNullValue());
        assertThat(clickMessage.getMetadata(), notNullValue());
        assertThat(clickMessage.getOriginalLink(), notNullValue());
        assertThat(clickMessage.getOs(), notNullValue());
        assertThat(clickMessage.getReceivedAt(), notNullValue());
        assertThat(clickMessage.getRecipient(), notNullValue());
        assertThat(clickMessage.getTag(), notNullValue());
        assertThat(clickMessage.getUserAgent(), notNullValue());
    }

    @Test
    public void testDelivery() throws Exception {
        // given
        URL json = ClassLoader.class.getResource("/webhook/delivery.json");

        // when
        WebhookMessage message = objectMapper.readValue(json, WebhookMessage.class);

        // then
        assertThat(message, instanceOf(DeliveryWebhookMessage.class));
        assertThat(message.getRecordType(), equalTo(WebhookRecordType.DELIVERY));

        DeliveryWebhookMessage deliveryMessage = (DeliveryWebhookMessage) message;
        assertThat(deliveryMessage.getMessageId(), equalTo("883953f4-6105-42a2-a16a-77a8eac79483"));
        assertThat(deliveryMessage.getDeliveredAt(), notNullValue());
        assertThat(deliveryMessage.getDetails(), notNullValue());
        assertThat(deliveryMessage.getMetadata(), notNullValue());
        assertThat(deliveryMessage.getRecipient(), notNullValue());
        assertThat(deliveryMessage.getTag(), notNullValue());
    }

    @Test
    public void testOpen() throws Exception {
        // given
        URL json = ClassLoader.class.getResource("/webhook/open.json");

        // when
        WebhookMessage message = objectMapper.readValue(json, WebhookMessage.class);

        // then
        assertThat(message, instanceOf(OpenWebhookMessage.class));
        assertThat(message.getRecordType(), equalTo(WebhookRecordType.OPEN));

        OpenWebhookMessage openMessage = (OpenWebhookMessage) message;
        assertThat(openMessage.getMessageId(), equalTo("883953f4-6105-42a2-a16a-77a8eac79483"));
        assertThat(openMessage.getClient(), notNullValue());
        assertThat(openMessage.getGeo(), notNullValue());
        assertThat(openMessage.getMetadata(), notNullValue());
        assertThat(openMessage.getOs(), notNullValue());
        assertThat(openMessage.getReceivedAt(), notNullValue());
        assertThat(openMessage.getUserAgent(), notNullValue());

    }

    @Test
    public void testSpamComplaint() throws Exception {
        // given
        URL json = ClassLoader.class.getResource("/webhook/spam-complaint.json");

        // when
        WebhookMessage message = objectMapper.readValue(json, WebhookMessage.class);

        // then
        assertThat(message, instanceOf(SpamComplaintWebhookMessage.class));
        assertThat(message.getRecordType(), equalTo(WebhookRecordType.SPAM_COMPLAINT));

        SpamComplaintWebhookMessage spamComplaintMessage = (SpamComplaintWebhookMessage) message;
        assertThat(spamComplaintMessage.getMessageId(), equalTo("00000000-0000-0000-0000-000000000000"));
        assertThat(spamComplaintMessage.getBouncedAt(), notNullValue());
        assertThat(spamComplaintMessage.getEmail(), notNullValue());
        assertThat(spamComplaintMessage.getMetadata(), notNullValue());
        assertThat(spamComplaintMessage.getSubject(), notNullValue());
        assertThat(spamComplaintMessage.getTag(), notNullValue());
    }

    @Test
    public void testSubscriptionChange() throws Exception {
        // given
        URL json = ClassLoader.class.getResource("/webhook/subscription-change.json");

        // when
        WebhookMessage message = objectMapper.readValue(json, WebhookMessage.class);

        // then
        assertThat(message, instanceOf(SubscriptionChangeWebhookMessage.class));
        assertThat(message.getRecordType(), equalTo(WebhookRecordType.SUBSCRIPTION_CHANGE));

        SubscriptionChangeWebhookMessage subscriptionChangeMessage = (SubscriptionChangeWebhookMessage) message;
        assertThat(subscriptionChangeMessage.getSuppressionReason(), notNullValue());
        assertThat(subscriptionChangeMessage.getChangedAt(), notNullValue());
        assertThat(subscriptionChangeMessage.getMetadata(), notNullValue());
        assertThat(subscriptionChangeMessage.getOrigin(), notNullValue());
        assertThat(subscriptionChangeMessage.getTag(), notNullValue());
    }
}