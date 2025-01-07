package lirosk.springrestauth.security.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth0.jwt.interfaces.DecodedJWT;

import lirosk.springrestauth.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtToUserConverter {
    private final UserRepository userRepository;

    public UserDetails convert(DecodedJWT decodedJWT) {
        String username = String.valueOf(decodedJWT.getClaim(Claims.USERNAME).asString());
        return userRepository.findByUsername(username);
    }
}
