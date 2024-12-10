package org.holubecky.adapters.in.web.exceptions;

public class InvalidCommandException extends RuntimeException{
    public InvalidCommandException(){
        super("Invalid command.");
    }
}
