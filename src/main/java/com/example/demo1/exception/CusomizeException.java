package com.example.demo1.exception;

public class CusomizeException extends RuntimeException {

    private String message;

    public CusomizeException(String message){
        this.message =message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
