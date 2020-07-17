package io.rocketbase.mail.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ConfigurationProperties(prefix = "postmark")
public class PostmarkProperties {

    private String token;
    private PostmarkApiConfig api = PostmarkApiConfig.DEFAULT;

    public PostmarkProperties(String token) {
        this.token = token;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostmarkApiConfig {
        public static PostmarkApiConfig DEFAULT = new PostmarkApiConfig("https://api.postmarkapp.com/", "X-Postmark-Server-Token");

        private String url;
        private String header;
    }

}
