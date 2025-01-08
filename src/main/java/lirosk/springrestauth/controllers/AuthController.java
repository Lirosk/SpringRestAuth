package lirosk.springrestauth.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lirosk.springrestauth.dto.AuthRequest;
import lirosk.springrestauth.dto.LoginResponse;
import lirosk.springrestauth.dto.RegisterRequest;
import lirosk.springrestauth.dto.UserInfo;
import lirosk.springrestauth.models.CustomUser;
import lirosk.springrestauth.repositories.CustomUserRepository;
import lirosk.springrestauth.security.jwt.JwtEncoder;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
public class AuthController {
    private final JwtEncoder jwtEncoder;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CustomUserRepository customUserRepository;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserInfo register(@RequestBody @Validated RegisterRequest authRequest) {
        if (customUserRepository.findByUsername(authRequest.getUsername()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }
        return UserInfo.of(customUserRepository.save(authRequest.toUser(passwordEncoder)));
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Validated AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUser customUser = (CustomUser) authentication.getPrincipal();

        String token = jwtEncoder.encode(customUser);
        return new LoginResponse(token);
    }
}
