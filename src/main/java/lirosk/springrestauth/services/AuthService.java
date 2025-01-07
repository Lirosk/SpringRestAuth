package lirosk.springrestauth.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lirosk.springrestauth.dto.AuthRequest;
import lirosk.springrestauth.dto.LoginResponse;
import lirosk.springrestauth.dto.RegisterRequest;
import lirosk.springrestauth.dto.UserInfo;
import lirosk.springrestauth.repositories.UserRepository;
import lirosk.springrestauth.security.jwt.JwtEncoder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserInfo attemptRegister(RegisterRequest authRequest) {
        if (userRepository.findByUsername(authRequest.getUsername()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }
        return UserInfo.of(userRepository.save(authRequest.toUser(List.of(new SimpleGrantedAuthority("ROLE_USER")), passwordEncoder)));
    }

    public LoginResponse attemptLogin(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails user = (UserDetails) authentication.getPrincipal();

        String token = jwtEncoder.encode(user);
        return new LoginResponse(token);
    }
}
