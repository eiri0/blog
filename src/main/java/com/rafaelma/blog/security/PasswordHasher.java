package com.rafaelma.blog.security;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class PasswordHasher {

    public static String hashPassword(String password) {
         Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(16, 32, 1, 60000, 10);
        return encoder.encode(password);
    }
   
}
