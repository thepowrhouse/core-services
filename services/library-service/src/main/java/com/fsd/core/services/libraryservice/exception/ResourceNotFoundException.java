package com.fsd.core.services.libraryservice.exception;

/**
 * Created by fayaz on 04-12-2017.
 */
public class ResourceNotFoundException extends RuntimeException {

    private Integer resourceId;

    public ResourceNotFoundException(Integer resourceId, String message) {
        super(message);
        this.resourceId = resourceId;
    }
}