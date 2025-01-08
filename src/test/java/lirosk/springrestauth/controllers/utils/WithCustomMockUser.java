package lirosk.springrestauth.controllers.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.test.context.support.WithSecurityContext;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithCustomMockUserSecurityContextFactory.class)
public @interface WithCustomMockUser {
    long userId() default 1L;

    String username() default "buzz";

    String password() default "qwe";

    String[] authorities() default "ROLE_USER";
}
