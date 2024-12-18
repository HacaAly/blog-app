package com.hikari.blog.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:secret.properties")
@ConfigurationProperties(prefix = "blog")
public class SecretProperties {

    private String userUsername;
    private String userPassword;
    private String JwtIss;
    private String JwtSecretKey;
}
