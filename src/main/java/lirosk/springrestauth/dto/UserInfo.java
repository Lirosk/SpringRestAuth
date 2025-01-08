package lirosk.springrestauth.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lirosk.springrestauth.models.CustomUser;

public record UserInfo(Long id, String username, Collection<? extends GrantedAuthority> authorities) {
    public static UserInfo of(CustomUser customUser) {
        return new UserInfo(customUser.getId(), customUser.getUsername(), customUser.getAuthorities());
    }
}
