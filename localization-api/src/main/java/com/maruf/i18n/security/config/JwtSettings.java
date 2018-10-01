package com.maruf.i18n.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtSettings {

    private Integer tokenExpirationTimeInMinute;

    private Integer refreshTokenExpTimeInMinute;

    private String tokenIssuer;

    private String tokenSigningKey;

}
