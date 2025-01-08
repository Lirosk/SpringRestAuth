package lirosk.springrestauth.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lirosk.springrestauth.dto.AuthRequest;
import lirosk.springrestauth.dto.LoginResponse;
import lirosk.springrestauth.dto.RegisterRequest;
import lirosk.springrestauth.dto.UserInfo;
import lirosk.springrestauth.services.AuthService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserInfo register(@RequestBody @Validated RegisterRequest authRequest) {
        return authService.attemptRegister(authRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Validated AuthRequest authRequest) {
        return authService.attemptLogin(authRequest);
    }
}
