package io.rocketbase.mail.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {

    private String messageStream;

    private String from;

    private String to;

    private String cc;

    private String bcc;

    private String replyTo;

    private String subject;

    private String htmlBody;

    private String textBody;

    private String tag;

    private List<Header> headers;

    @Deprecated
    private List<Map<String, String>> attachments;

    private Boolean trackOpens;

    private TrackLinksType trackLinks;

    private Map<String, String> metadata;


    public Message(String from, String to, String subject, String htmlBody, String textBody) {
        setFrom(from);
        setTo(to);
        this.subject = subject;
        this.htmlBody = htmlBody;
        this.textBody = textBody;
    }

    public Message(EmailAddress from, EmailAddress to, String subject, String htmlBody, String textBody) {
        setFrom(from);
        setTo(to);
        this.subject = subject;
        this.htmlBody = htmlBody;
        this.textBody = textBody;
    }

    @JsonCreator
    public Message(@JsonProperty("from") String from,
                   @JsonProperty("to") String to,
                   @JsonProperty("subject") String subject,
                   @JsonProperty("htmlBody") String htmlBody,
                   @JsonProperty("textBody") String textBody,
                   @JsonProperty("cc") String cc,
                   @JsonProperty("bcc") String bcc) {
        setFrom(from);
        setTo(to);
        this.subject = subject;
        this.htmlBody = htmlBody;
        this.textBody = textBody;
        setCc(cc);
        setBcc(bcc);
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setFrom(EmailAddress from) {
        this.from = from != null ? from.toRecipient() : null;
    }

    public void setTo(EmailAddress to) {
        this.to = to != null ? to.toRecipient() : null;
    }

    public void setTo(List<EmailAddress> to) {
        this.to = emailAddressList(to);
    }

    public void setTo(String... to) {
        this.to = emailList(to);
    }

    @JsonSetter
    public void setTo(String to) {
        this.to = to;
    }

    public void setCc(EmailAddress cc) {
        this.cc = cc != null ? cc.toRecipient() : null;
    }

    public void setCc(List<EmailAddress> cc) {
        this.cc = emailAddressList(cc);
    }

    public void setCc(String... cc) {
        this.cc = emailList(cc);
    }

    @JsonSetter
    public void setCc(String cc) {
        this.cc = cc;
    }

    public void setBcc(EmailAddress bcc) {
        this.bcc = bcc != null ? bcc.toRecipient() : null;
    }

    public void setBcc(List<EmailAddress> bcc) {
        this.bcc = emailAddressList(bcc);
    }

    public void setBcc(EmailAddress... bcc) {
        this.bcc = emailList(bcc);
    }

    public void setBcc(String... bcc) {
        this.bcc = emailList(bcc);
    }

    @JsonSetter
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    @Deprecated
    public void addAttachment(String path) {
        this.addAttachment((new File(path)).getName(), readFileContent(path), readFileContentType(path));
    }

    @Deprecated
    public void addAttachment(String path, String contentId) {
        this.addAttachment((new File(path)).getName(), readFileContent(path), readFileContentType(path), contentId);
    }

    @Deprecated
    public void addAttachment(String filename, String content, String contentType, String contentId) {
        this.addAttachment(filename, content.getBytes(), contentType, contentId);
    }

    @Deprecated
    public void addAttachment(String filename, String content, String contentType) {
        this.addAttachment(filename, content.getBytes(), contentType);
    }

    @Deprecated
    public void addAttachment(String name, byte[] content, String contentType) {
        Map<String, String> attachment = new HashMap();
        attachment.put("Name", name);
        attachment.put("Content", Base64.getEncoder().encodeToString(content));
        attachment.put("ContentType", contentType);
        this.addAttachment((Map) attachment);
    }

    @Deprecated
    public void addAttachment(String name, byte[] content, String contentType, String contentId) {
        Map<String, String> attachment = new HashMap();
        attachment.put("Name", name);
        attachment.put("Content", Base64.getEncoder().encodeToString(content));
        attachment.put("ContentType", contentType);
        attachment.put("ContentId", contentId);
        this.addAttachment(attachment);
    }

    @Deprecated
    public void addAttachment(Map<String, String> attachment) {
        if (this.attachments == null) {
            this.attachments = new ArrayList();
        }

        this.attachments.add(attachment);
    }

    @Deprecated
    public void addAttachments(List<Map<String, String>> attachments) {
        attachments.forEach(this::addAttachment);
    }

    @SneakyThrows
    protected byte[] readFileContent(String path) {
        return Files.readAllBytes(Paths.get(path));
    }

    @SneakyThrows
    protected String readFileContentType(String path) {
        return Files.probeContentType(new File(path).toPath());
    }

    protected String emailAddressList(List<EmailAddress> addresses) {
        if (addresses == null || addresses.isEmpty()) {
            return null;
        }
        return addresses.stream()
                .map(EmailAddress::toRecipient)
                .collect(Collectors.joining(","));
    }

    protected String emailList(String... addresses) {
        if (addresses == null || addresses.length == 0) {
            return null;
        }
        return Arrays.asList(addresses)
                .stream()
                .map(e -> new EmailAddress(e).toRecipient())
                .collect(Collectors.joining(","));
    }

    protected String emailList(EmailAddress... addresses) {
        if (addresses == null || addresses.length == 0) {
            return null;
        }
        return Arrays.asList(addresses)
                .stream()
                .map(EmailAddress::toRecipient)
                .collect(Collectors.joining(","));
    }
}
