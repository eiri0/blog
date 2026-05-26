package com.rafaelma.blog.security;

import org.mindrot.jbcrypt.BCrypt;


public class PasswordHasher {

    public static String hashPassword(String password, String salt) {
        return BCrypt.hashpw(password, salt);
    }
    
    public static String generateSalt() {
        int logRounds = 12;
        return BCrypt.gensalt(logRounds);
    }
}
