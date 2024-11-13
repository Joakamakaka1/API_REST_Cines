package com.es.diecines.errores;

/**
 * The type Bad request exception.
 */
public class BadRequestException extends RuntimeException {

    private static final String DESCRIPTION = "Bad Request Exception (400)";

    /**
     * Instantiates a new Bad request exception.
     *
     * @param detail the detail
     */
    public BadRequestException(String detail) {
        super(DESCRIPTION+". "+detail);
    }

}
