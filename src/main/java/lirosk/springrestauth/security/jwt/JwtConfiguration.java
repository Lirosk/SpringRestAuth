package lirosk.springrestauth.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("security.jwt")
public class JwtConfiguration {
    private String secretKey;
}
