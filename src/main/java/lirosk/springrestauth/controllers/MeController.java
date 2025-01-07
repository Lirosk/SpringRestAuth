package lirosk.springrestauth.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lirosk.springrestauth.dto.UserInfo;

@RestController
@RequestMapping(path = "/me")
public class MeController {
    @GetMapping
    public UserInfo getMe(@AuthenticationPrincipal UserDetails user) {
        return UserInfo.of(user);
    }

    @GetMapping(path = "/username")
    public String getUsername(@AuthenticationPrincipal UserDetails user) {
        return user.getUsername();
    }
}
