package lirosk.springrestauth.security.jwt;

import org.springframework.stereotype.Component;

import com.auth0.jwt.interfaces.DecodedJWT;

import lirosk.springrestauth.models.CustomUser;
import lirosk.springrestauth.repositories.CustomUserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtToUserConverter {
    private final CustomUserRepository customUserRepository;

    public CustomUser convert(DecodedJWT decodedJWT) {
        Long id = Long.valueOf(decodedJWT.getSubject());
        String username = String.valueOf(decodedJWT.getClaim("username").asString());
        return customUserRepository.findByIdAndUsername(id, username);
    }
}
