package com.example.BackendApp.exceptions;

public class UserAlreadyExistException extends Exception{

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
