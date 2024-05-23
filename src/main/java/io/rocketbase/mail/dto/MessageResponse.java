package io.rocketbase.mail.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageResponse {

    private String to;
    private String cc;
    private String bcc;
    private Date submittedAt;
    private String messageId;
    private Integer errorCode;
    private String message;
}
