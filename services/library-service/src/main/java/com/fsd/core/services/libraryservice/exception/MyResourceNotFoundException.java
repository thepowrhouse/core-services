package com.fsd.core.services.libraryservice.exception;

/**
 * Created by fayaz on 28-11-2017.
 */
public class MyResourceNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 4088649120307193208L;

    public MyResourceNotFoundException() {
        super();
    }

    public MyResourceNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public MyResourceNotFoundException(final String message) {
        super(message);
    }

    public MyResourceNotFoundException(final Throwable cause) {
        super(cause);
    }

}