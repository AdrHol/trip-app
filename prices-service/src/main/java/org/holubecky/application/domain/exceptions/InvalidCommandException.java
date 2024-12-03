package org.holubecky.application.domain.exceptions;

public class InvalidCommandException extends RuntimeException{
    public InvalidCommandException(){
        super("Invalid command.");
    }
}
