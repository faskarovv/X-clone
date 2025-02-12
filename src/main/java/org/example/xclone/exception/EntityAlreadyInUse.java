package org.example.xclone.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

public class EntityAlreadyInUse extends RuntimeException{


    public EntityAlreadyInUse(String message) {
        super(message);
    }
}
