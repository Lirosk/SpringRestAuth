package lirosk.springrestauth;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/me")
public class MeController {
    @GetMapping
    public User getMe(@AuthenticationPrincipal User user) {
        return user;
    }

    @GetMapping(path = "/username")
    public String getUsername(@AuthenticationPrincipal User user) {
        return user.getUsername();
    }
}
