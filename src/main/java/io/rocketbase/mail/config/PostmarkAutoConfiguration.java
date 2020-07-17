package io.rocketbase.mail.config;

import io.rocketbase.mail.PostmarkClient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Configuration
@EnableConfigurationProperties({PostmarkProperties.class})
@RequiredArgsConstructor
public class PostmarkAutoConfiguration implements Serializable {

    private final PostmarkProperties postmarkProperties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "postmark.enabled", matchIfMissing = true)
    public PostmarkClient postmarkClient() {
        return new PostmarkClient(postmarkProperties);
    }

}
