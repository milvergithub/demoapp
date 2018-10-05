package com.milver.web.error;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String exception) {
        super(exception);
    }
}
