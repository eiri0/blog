package com.rafaelma.blog.security;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class PasswordHasher {

    private static final int SALT_LENGTH = 16;
    private static final int HASH_LENGTH = 32;
    private static final int PARALLELISM = 1;
    private static final int MEMORY = 60000;
    private static final int ITERATIONS = 10;
    private static Argon2PasswordEncoder encoder ;
    
    public static String hashPassword(String password) {
        encoder = new Argon2PasswordEncoder(SALT_LENGTH, HASH_LENGTH, PARALLELISM, MEMORY, ITERATIONS);
        return encoder.encode(password);
    }
   
}
