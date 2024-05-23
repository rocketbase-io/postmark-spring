package io.rocketbase.mail.dto;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class EmailAddressTest {

    @Test
    void parseSimple() {
        EmailAddress email = EmailAddress.parse("<marten@rocketbase.io>");
        assertThat(email, equalTo(new EmailAddress("marten@rocketbase.io")));
    }

    @Test
    void parseCombined() {
        EmailAddress whiteSpace = EmailAddress.parse("\"Marten Prieß\" <marten@rocketbase.io>");
        EmailAddress without = EmailAddress.parse("\"Marten Prieß\"<marten@rocketbase.io>");
        EmailAddress compared = new EmailAddress("marten@rocketbase.io", "Marten Prieß");
        assertThat(whiteSpace, equalTo(compared));
        assertThat(without, equalTo(compared));
    }

    @Test()
    void checkFailure() {
        try {
            EmailAddress error = EmailAddress.parse("\"Marten Prieß\" <marten@rocketbase.io");
        } catch (Exception e) {
            assertThat(e, instanceOf(IllegalArgumentException.class));
        }
    }
}