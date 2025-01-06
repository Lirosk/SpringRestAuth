package lirosk.springrestauth.dto;

import org.springframework.security.crypto.password.PasswordEncoder;

import lirosk.springrestauth.models.CustomUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RegisterRequest extends AuthRequest {
    public CustomUser toUser(PasswordEncoder passwordEncoder) {
        return new CustomUser(getUsername(), passwordEncoder.encode(getPassword()));
    }
}
