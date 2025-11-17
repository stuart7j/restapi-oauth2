package com.jsservicespac.restapioauth2;

import com.jsservicespac.restapioauth2.service.KeyGeneratorUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class RestapiOauth2Application {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        SpringApplication.run(RestapiOauth2Application.class, args);
        KeyGeneratorUtil keyGeneratorUtil = new KeyGeneratorUtil();
        keyGeneratorUtil.generateKey();
    }

}
