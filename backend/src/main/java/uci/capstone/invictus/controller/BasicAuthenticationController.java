package uci.capstone.invictus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uci.capstone.invictus.authentication.AuthenticationBean;

@RestController
public class BasicAuthenticationController {

    @GetMapping(path = "/login")
    public AuthenticationBean login() {
        return new AuthenticationBean("You are authenticated");
    }

    @GetMapping(path = "/logout")
    public AuthenticationBean logout() {
        return new AuthenticationBean("You are logged out");
    }
}