package com.jsservicespac.restapioauth2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ApiController {

    @GetMapping("/")
    public String home() {
        return "Welcome to the public API!";
    }

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint.";
    }

    @GetMapping("/private")
    public String privateEndpoint(Principal principal) {
        return "Welcome " + principal.getName() + ", this is a protected endpoint.";
    }

    @GetMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }
}
