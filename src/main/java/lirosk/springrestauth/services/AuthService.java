package lirosk.springrestauth.services;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lirosk.springrestauth.dto.AuthRequest;
import lirosk.springrestauth.dto.LoginResponse;
import lirosk.springrestauth.dto.UserInfo;
import lirosk.springrestauth.models.CustomUser;
import lirosk.springrestauth.repositories.CustomUserRepository;
import lirosk.springrestauth.security.jwt.JwtEncoder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtEncoder jwtEncoder;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CustomUserRepository customUserRepository;

    public UserInfo attemptRegister(AuthRequest authRequest) {
        if (customUserRepository.findByUsername(authRequest.username()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }
        return UserInfo.of(customUserRepository.save(authRequest.toUser(passwordEncoder)));
    }

    public LoginResponse attemptLogin(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUser customUser = (CustomUser) authentication.getPrincipal();

        String token = jwtEncoder.encode(customUser);
        return new LoginResponse(token);
    }
}
