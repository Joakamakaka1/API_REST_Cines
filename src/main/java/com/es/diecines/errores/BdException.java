package com.es.diecines.errores;

public class BdException extends RuntimeException{
    private static final String MESSAGE = "Error en la base de datos";
    public BdException(String message, Exception e) {
        super(MESSAGE + ": " + message, e);
    }

    public BdException(String message) {
        super(MESSAGE + ": " + message);
    }
}
