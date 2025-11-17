package com.jsservicespac.restapioauth2.controller;

import com.jsservicespac.restapioauth2.service.KeyGeneratorUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;

@RestController
@AllArgsConstructor
public class ApiController {

    KeyGeneratorUtil keyGeneratorUtil;

    @GetMapping("/")
    public String home() throws NoSuchAlgorithmException, IOException {

        keyGeneratorUtil.generateKey();
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
