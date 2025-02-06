package org.example.xclone.exception;

public class EntityAlreadyInUse extends RuntimeException{
    public EntityAlreadyInUse(String message) {
        super(message);
    }
}
