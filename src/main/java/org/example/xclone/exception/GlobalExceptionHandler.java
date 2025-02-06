package org.example.xclone.exception;


import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public String  entityNotFoundException(){

        return "Entity not found";
    }

}
