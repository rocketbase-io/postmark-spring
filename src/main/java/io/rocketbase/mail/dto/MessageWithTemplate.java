package io.rocketbase.mail.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageWithTemplate {

    private Long templateId;

    private String templateAlias;

    private Object templateModel;

    private boolean inlineCss;

    private String from;

    private String to;

    private String cc;

    private String bcc;

    private String replyTo;

    private String tag;

    private List<Header> headers;

    private Boolean trackOpens;

    private TrackLinksType trackLinks;

    private Map<String, String> metadata;


    public MessageWithTemplate(String from, String to, Long templateId, Object templateModel) {
        setFrom(from);
        setTo(to);
        this.templateId = templateId;
        this.templateModel = templateModel;
    }

    public MessageWithTemplate(EmailAddress from, EmailAddress to, String templateAlias, Object templateModel) {
        setFrom(from);
        setTo(to);
        this.templateAlias = templateAlias;
        this.templateModel = templateModel;
    }

    @JsonCreator
    public MessageWithTemplate(@JsonProperty("from") String from,
                               @JsonProperty("to") String to,
                               @JsonProperty("templateId") Long templateId,
                               @JsonProperty("templateAlias") String templateAlias,
                               @JsonProperty("cc") String cc,
                               @JsonProperty("bcc") String bcc) {
        setFrom(from);
        setTo(to);
        this.templateId = templateId;
        this.templateAlias = templateAlias;
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

    @SneakyThrows
    protected byte[] readFileContent(String path) {
        return Files.readAllBytes(Paths.get(path));
    }

    @SneakyThrows
    protected String readFileContentType(String path) {
        return Files.probeContentType(new File(path).toPath());
    }

    public void setTemplateModel(Object templateModel) {
        this.templateModel = templateModel;
    }

    public void setInlineCss(boolean inlineCss) {
        this.inlineCss = inlineCss;
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
