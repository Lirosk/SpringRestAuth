package lirosk.springrestauth.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAuthToken extends AbstractAuthenticationToken {
    private final UserDetails user;

    public UserAuthToken(UserDetails user) {
        super(user.getAuthorities());
        this.user = user;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public UserDetails getPrincipal() {
        return user;
    }
}
