package io.rocketbase.mail.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class MessageResponse {

    private String to;
    private String cc;
    private String bcc;
    private Date submittedAt;
    private String messageId;

    private Integer errorCode;
    private String message;
}
