package lirosk.springrestauth.security.jwt;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lirosk.springrestauth.models.CustomUser;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtEncoder {
    private final JwtConfiguration jwtConfiguration;

    public String encode(CustomUser customUser) {
        return JWT.create()
                .withSubject(String.valueOf(customUser.getId()))
                .withExpiresAt(Instant.now().plus(Duration.of(30, ChronoUnit.MINUTES)))
                .withClaim(Claims.ID, customUser.getId())
                .withClaim(Claims.USERNAME, customUser.getUsername())
                .sign(Algorithm.HMAC256(jwtConfiguration.getSecretKey()));
    }
}
