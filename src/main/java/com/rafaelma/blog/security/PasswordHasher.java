package com.rafaelma.blog.security;

import org.mindrot.jbcrypt.BCrypt;


public class PasswordHasher {

    public static String hashPassword(String password) {
        int rounds = 12;
        return BCrypt.gensalt(rounds);
    }
   
}
