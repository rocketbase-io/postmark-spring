package io.rocketbase.mail.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@EqualsAndHashCode(of = {"email", "name"})
@Getter
@RequiredArgsConstructor
public class EmailAddress {

    private final String email;
    private final String name;

    private static final Pattern NAME_EMAIL_PATTERN = Pattern.compile("\"([^\"]*)\"\\s*<([^>]*)>");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("<([^>]*)>");

    public EmailAddress(String email) {
        this.email = email;
        this.name = null;
    }

    public static EmailAddress parse(String email) {
        Matcher emailNameMatcher = NAME_EMAIL_PATTERN.matcher(email);
        if (emailNameMatcher.matches()) {
            return new EmailAddress(emailNameMatcher.group(2),emailNameMatcher.group(1) );
        }
        Matcher emailMatcher = EMAIL_PATTERN.matcher(email);
        if (emailMatcher.matches()) {
            return new EmailAddress(emailMatcher.group(1) );
        }
        throw new IllegalArgumentException("Invalid email address: " + email);
    }

    public String toRecipient() {
        if (!StringUtils.isEmpty(name)) {
            return "\"%s\" <%s>".formatted(name, email);
        } else {
            return "<%s>".formatted(email);
        }
    }

    @Override
    public String toString() {
        return toRecipient();
    }
}
