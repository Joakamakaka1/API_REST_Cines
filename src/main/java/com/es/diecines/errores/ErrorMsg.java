package com.es.diecines.errores;

/**
 * The type Error msg.
 */
public class ErrorMsg {
    private String exception;
    private String message;
    private String path;

    /**
     * Instantiates a new Error msg.
     *
     * @param exception the exception
     * @param path      the path
     */
    public ErrorMsg(Exception exception, String path) {
        this.exception = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
        this.path = path;
    }

    /**
     * Instantiates a new Error msg.
     *
     * @param notFound the not found
     * @param path     the path
     * @param i        the
     */
    public ErrorMsg(String notFound, String path, int i) {
        this.exception = notFound;
        this.message = notFound;
        this.path = path;
    }

    /**
     * Gets exception.
     *
     * @return the exception
     */
    public String getException() {
        return exception;
    }

    /**
     * Sets exception.
     *
     * @param exception the exception
     */
    public void setException(String exception) {
        this.exception = exception;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets path.
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets path.
     *
     * @param path the path
     */
    public void setPath(String path) {
        this.path = path;
    }
}
