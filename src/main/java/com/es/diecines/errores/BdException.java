package com.es.diecines.errores;

/**
 * The type Bd exception.
 */
public class BdException extends RuntimeException {
    private static final String MESSAGE = "Error en la base de datos";

    /**
     * Instantiates a new Bd exception.
     *
     * @param message the message
     * @param e       the e
     */
    public BdException(String message, Exception e) {
        super(MESSAGE + ": " + message, e);
    }

    /**
     * Instantiates a new Bd exception.
     *
     * @param message the message
     */
    public BdException(String message) {
        super(MESSAGE + ": " + message);
    }
}
