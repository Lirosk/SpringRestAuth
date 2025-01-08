package lirosk.springrestauth.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import lirosk.springrestauth.models.CustomUser;

public class UserAuthToken extends AbstractAuthenticationToken {
    private final CustomUser customUser;

    public UserAuthToken(CustomUser customUser) {
        super(customUser.getAuthorities());
        this.customUser = customUser;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public CustomUser getPrincipal() {
        return customUser;
    }
}
