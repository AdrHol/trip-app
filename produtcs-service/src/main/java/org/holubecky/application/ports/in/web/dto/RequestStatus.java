package org.holubecky.application.ports.in.web.dto;

public enum RequestStatus {
    ENTITY_FOUND(true), ENTITY_NOT_FOUND(false);

    private final boolean status;
    RequestStatus(boolean status){
        this.status = status;
    }

    public boolean get(){
        return this.status;
    }
}
