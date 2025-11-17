package com.jsservicespac.restapioauth2.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

@Service
@AllArgsConstructor
@Slf4j
public class KeyGeneratorUtil {

    public void generateKey() throws NoSuchAlgorithmException, IOException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256); // AES-256
        SecretKey secretKey = keyGen.generateKey();

        // Convert the raw bytes to Base64 (this is what you want to copy)
        String base64Key = Base64.getEncoder().encodeToString(secretKey.getEncoded());

        // This is the line you need
        System.out.println("Base64-encoded encryption key (COPY THIS):");
        System.out.println(base64Key);
        System.out.println();
        System.out.println("Length: " + base64Key.length() + " characters (should be 44 for AES-256)");
    }
}
