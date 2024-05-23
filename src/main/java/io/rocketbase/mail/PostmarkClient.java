package io.rocketbase.mail;

import io.rocketbase.mail.config.PostmarkProperties;
import io.rocketbase.mail.dto.EmailAttachment;
import io.rocketbase.mail.dto.Message;
import io.rocketbase.mail.dto.MessageResponse;
import io.rocketbase.mail.dto.MessageWithTemplate;
import io.rocketbase.mail.util.MessageJsonWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.util.List;

@RequiredArgsConstructor
public class PostmarkClient {

    private final PostmarkProperties postmarkProperties;

    protected RestTemplate restTemplate;

    protected RestTemplate getRestTemplate() {
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }
        return restTemplate;
    }

    public MessageResponse deliverMessage(Message msg) {
        ResponseEntity<MessageResponse> response = getRestTemplate().exchange(createUriBuilder().path("/email/")
                        .toUriString(),
                HttpMethod.POST,
                new HttpEntity<>(msg, buildHeaders()),
                MessageResponse.class);
        return response.getBody();
    }

    public MessageResponse deliverMessage(Message msg, EmailAttachment... emailAttachments) {
        ResponseEntity<MessageResponse> response = null;
        File json = null;
        try {
            json = new MessageJsonWriter().writeMessageFile(msg, emailAttachments);
            response = getRestTemplate().exchange(createUriBuilder().path("/email/")
                            .toUriString(),
                    HttpMethod.POST,
                    new HttpEntity<>(new FileSystemResource(json), buildHeaders()),
                    MessageResponse.class);
        } finally {
            if (json != null) {
                json.delete();
            }
        }
        return response.getBody();
    }

    public List<MessageResponse> deliverMessage(List<Message> messages) {
        ResponseEntity<List<MessageResponse>> response = getRestTemplate().exchange(createUriBuilder().path("/email/batch")
                        .toUriString(),
                HttpMethod.POST,
                new HttpEntity<>(messages, buildHeaders()),
                new ParameterizedTypeReference<List<MessageResponse>>() {
                });
        return response.getBody();
    }

    public MessageResponse deliverMessageWithTemplate(MessageWithTemplate msg) {
        ResponseEntity<MessageResponse> response = getRestTemplate().exchange(createUriBuilder().path("/email/withTemplate")
                        .toUriString(),
                HttpMethod.POST,
                new HttpEntity<>(msg, buildHeaders()),
                MessageResponse.class);
        return response.getBody();
    }

    protected UriComponentsBuilder createUriBuilder() {
        return UriComponentsBuilder.fromUriString(postmarkProperties.getApi().getUrl());
    }

    protected HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(postmarkProperties.getApi().getHeader(), postmarkProperties.getToken());
        return headers;
    }

}
