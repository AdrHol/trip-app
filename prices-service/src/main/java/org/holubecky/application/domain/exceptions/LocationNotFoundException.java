package org.holubecky.application.domain.exceptions;

public class LocationNotFoundException extends RuntimeException{

    public LocationNotFoundException(){
        super("Error with response from location API");
    }
}
