package io.rocketbase.mail.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

import java.io.File;

@Data
@RequiredArgsConstructor
public class EmailAttachment {

    private final String name;

    private final File file;

    @Nullable
    private String contentId;

    public EmailAttachment withContentId(String contentId) {
        setContentId(contentId);
        return this;
    }
}
