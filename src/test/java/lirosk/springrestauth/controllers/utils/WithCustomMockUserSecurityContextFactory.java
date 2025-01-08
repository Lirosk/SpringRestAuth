package lirosk.springrestauth.controllers.utils;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import lirosk.springrestauth.models.CustomUser;
import lirosk.springrestauth.security.UserAuthToken;

public class WithCustomMockUserSecurityContextFactory implements WithSecurityContextFactory<WithCustomMockUser> {
    @Override
    public SecurityContext createSecurityContext(WithCustomMockUser annotation) {
        var authorities = Arrays.stream(annotation.authorities()).map(SimpleGrantedAuthority::new).toList();

        var customUser = new CustomUser(annotation.username(), annotation.password());
        customUser.setId(annotation.userId());

        var context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(new UserAuthToken(customUser));
        return context;
    }
}
