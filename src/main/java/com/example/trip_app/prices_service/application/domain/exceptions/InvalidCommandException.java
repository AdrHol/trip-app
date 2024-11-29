package com.example.trip_app.prices_service.application.domain.exceptions;

public class InvalidCommandException extends RuntimeException{
    public InvalidCommandException(){
        super("Invalid command.");
    }
}
