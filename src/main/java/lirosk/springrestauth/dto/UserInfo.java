package lirosk.springrestauth.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public record UserInfo(String username, Collection<? extends GrantedAuthority> authorities) {
    public static UserInfo of(UserDetails userDetails) {
        return new UserInfo(userDetails.getUsername(), userDetails.getAuthorities());
    }
}
