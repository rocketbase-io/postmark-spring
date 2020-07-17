package io.rocketbase.mail.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@RequiredArgsConstructor
public class EmailAddress {

    private final String email;

    private final String name;

    public EmailAddress(String email) {
        this.email = email;
        this.name = null;
    }

    public String toRecipient() {
        if (!StringUtils.isEmpty(name)) {
            return String.format("\"%s\" <%s>", name, email);
        } else {
            return String.format("<%s>", email);
        }
    }
}
