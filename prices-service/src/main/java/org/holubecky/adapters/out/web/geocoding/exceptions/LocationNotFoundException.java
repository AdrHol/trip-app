package org.holubecky.adapters.out.web.geocoding.exceptions;

public class LocationNotFoundException extends RuntimeException{
    public LocationNotFoundException(){
        super("Error with response from location API");
    }
}
