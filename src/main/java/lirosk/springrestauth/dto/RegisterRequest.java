package lirosk.springrestauth.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RegisterRequest extends AuthRequest {
    public UserDetails toUser(Collection<? extends GrantedAuthority> authorities, PasswordEncoder passwordEncoder) {
        return new UserDetails() {
            private final String username = getUsername();
            private final String password = passwordEncoder.encode(getPassword());

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return authorities;
            }

            @Override
            public String getPassword() {
                return password;
            }

            @Override
            public String getUsername() {
                return username;
            }
        };
    }
}
