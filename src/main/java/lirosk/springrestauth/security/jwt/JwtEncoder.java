package lirosk.springrestauth.security.jwt;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtEncoder {
    private final JwtConfiguration jwtConfiguration;

    public String encode(UserDetails userDetails) {
        return JWT.create()
                .withExpiresAt(Instant.now().plus(Duration.of(30, ChronoUnit.MINUTES)))
                .withClaim(Claims.USERNAME, userDetails.getUsername())
                .sign(Algorithm.HMAC256(jwtConfiguration.getSecretKey()));
    }
}
