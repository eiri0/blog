package com.rafaelma.blog.user.exception;

import java.text.MessageFormat;

public class UserAlreadyExistsException extends RuntimeException {
    
    public UserAlreadyExistsException(String userName) {
        super(MessageFormat.format("User {0} already exists", userName));
    }
}
