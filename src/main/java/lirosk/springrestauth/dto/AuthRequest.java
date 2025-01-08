package lirosk.springrestauth.dto;

import org.springframework.security.crypto.password.PasswordEncoder;

import lirosk.springrestauth.models.CustomUser;

public record AuthRequest(String username, String password) {
    public CustomUser toUser(PasswordEncoder passwordEncoder) {
        return new CustomUser(username, passwordEncoder.encode(password));
    }
}
