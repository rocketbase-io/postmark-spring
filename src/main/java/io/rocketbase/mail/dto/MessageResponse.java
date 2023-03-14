package io.rocketbase.mail.dto;

import java.util.Date;

public class MessageResponse {

    private String to;
    private String cc;
    private String bcc;
    private Date submittedAt;
    private String messageId;
    private Integer errorCode;
    private String message;
}
