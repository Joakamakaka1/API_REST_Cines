package com.es.diecines.errores;

/**
 * The type Not found exception.
 */
public class NotFoundException extends RuntimeException {
    private static final String DESCRIPTION = "Not Found Exception (404)";

    /**
     * Instantiates a new Not found exception.
     *
     * @param message the message
     */
    public NotFoundException(String message) {
        super(DESCRIPTION + ": " + message);
    }

    /**
     * Instantiates a new Not found exception.
     *
     * @param message the message
     * @param e       the e
     */
    public NotFoundException(String message, Exception e) {
        super(DESCRIPTION + ": " + message, e);
    }
}
