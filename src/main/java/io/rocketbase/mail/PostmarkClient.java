package io.rocketbase.mail;

import io.rocketbase.mail.dto.EmailAttachment;
import io.rocketbase.mail.dto.Message;
import io.rocketbase.mail.dto.MessageResponse;
import io.rocketbase.mail.dto.MessageWithTemplate;

import java.util.List;

public interface PostmarkClient {

    MessageResponse deliverMessage(Message msg);

    MessageResponse deliverMessage(Message msg, EmailAttachment... emailAttachments);

    List<MessageResponse> deliverMessage(List<Message> messages);

    MessageResponse deliverMessageWithTemplate(MessageWithTemplate msg);
}
