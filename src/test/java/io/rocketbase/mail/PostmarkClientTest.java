package io.rocketbase.mail;


import io.rocketbase.mail.config.PostmarkProperties;
import io.rocketbase.mail.dto.EmailAddress;
import io.rocketbase.mail.dto.Message;
import io.rocketbase.mail.dto.MessageResponse;
import io.rocketbase.mail.dto.TrackLinksType;
import org.junit.Ignore;
import org.junit.Test;

import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;


public class PostmarkClientTest {

    @Ignore
    @Test
    public void testDeliverMessage() {
        // given
        PostmarkClient client = new PostmarkClient(new PostmarkProperties("--"));
        Message message = Message.builder()
                .from("info@rocketbase.io")
                .to(new EmailAddress("marten@rocketbase.io", "Marten Prieß").toRecipient())
                .subject("Postmark Spring-Client")
                .textBody("My little Text\nGreat to hear..\nhttp://www.rocketbase.io")
                .htmlBody("<h1>My little Text</h2><p>Great to hear...</p><p><a href=\"http://www.rocketbase.io\">Link</a>")
                .build();

        URL logo = ClassLoader.class.getResource("/logo-dark.png");
        message.addAttachment(logo.getFile());
        message.setTrackLinks(TrackLinksType.HTML_AND_TEXT);

        // when
        MessageResponse response = client.deliverMessage(message);
        // then
        assertThat(response, notNullValue());
    }
}