package io.rocketbase.mail;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.rocketbase.mail.config.PostmarkProperties;
import io.rocketbase.mail.dto.*;
import io.rocketbase.mail.util.MessageJsonWriter;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PostmarkClientTest {

    @Disabled
    @Test
    public void testDeliverMessage() {
        // given
        PostmarkClient client = new DefaultPostmarkClient(new PostmarkProperties("--"));
        Message message = new Message(new EmailAddress("info@rocketbase.io"),
                new EmailAddress("marten@rocketbase.io", "Marten Prieß"), "Postmark Spring-Client",
                "<h1>My little Text</h2><p>Great to hear...</p><p><a href=\"http://www.rocketbase.io\">Link</a>",
                "My little Text\nGreat to hear..\nhttp://www.rocketbase.io");

        URL logo = ClassLoader.getSystemResource("logo-dark.png");
        message.addAttachment(logo.getFile());
        message.setTrackLinks(TrackLinksType.HTML_AND_TEXT);

        // when
        MessageResponse response = client.deliverMessage(message);
        // then
        assertThat(response, notNullValue());
    }


    @Disabled
    @Test
    public void testDeliverMessageWithTemplate() {
        // given
        Map templateObject = new HashMap();
        templateObject.put("product_name", "RocketBase");
        templateObject.put("product_url", "https://www.rocketbase.io");
        templateObject.put("company_name", "RocketBase");
        templateObject.put("company_address", "Katharinenstraße 30a, 20457 Hamburg");
        templateObject.put("task_name", "task-name");
        templateObject.put("message", "Had some issues...\nplease take a look");

        PostmarkClient client = new DefaultPostmarkClient(new PostmarkProperties("--"));
        MessageWithTemplate message = new MessageWithTemplate(new EmailAddress("info@rocketbase.io"),
                new EmailAddress("marten@rocketbase.io", "Marten Prieß"), "application-error", templateObject);

        // when
        MessageResponse response = client.deliverMessageWithTemplate(message);
        // then
        assertThat(response, notNullValue());
    }

    @Test
    public void testBuildJson() throws Exception {
        // given
        Message message = new Message(new EmailAddress("info@rocketbase.io"),
                new EmailAddress("marten@rocketbase.io", "Marten Prieß"), "Postmark Spring-Client",
                "<h1>My little Text</h2><p>Great to hear...</p><p><a href=\"http://www.rocketbase.io\">Link</a>",
                "My little Text\nGreat to hear..\nhttp://www.rocketbase.io");

        URL logo = ClassLoader.getSystemResource("logo-dark.png");
        MessageJsonWriter writer = new MessageJsonWriter();

        // when
        File result = writer.writeMessageFile(message, new EmailAttachment("logo-dark.png", new File(logo.toURI())));
        // then
        assertThat(result, notNullValue());
        Message parsedResult = new ObjectMapper().readValue(result, Message.class);
        assertThat(parsedResult.getAttachments()
                .size(), equalTo(1));
        result.delete();
    }
}