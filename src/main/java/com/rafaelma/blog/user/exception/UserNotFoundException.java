package com.rafaelma.blog.user.exception;

import java.text.MessageFormat;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super(MessageFormat.format("User {0} not found", id));
    }
}
