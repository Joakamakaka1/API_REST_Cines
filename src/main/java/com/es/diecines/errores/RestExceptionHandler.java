package com.es.diecines.errores;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.BadRequestException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * The type Rest exception handler.
 */
@ControllerAdvice
public class RestExceptionHandler {
    /**
     * Handle not found error msg.
     *
     * @param request   the request
     * @param exception the exception
     * @return the error msg
     */
    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMsg handleNotFound(HttpServletRequest request, Exception exception) {
        return new ErrorMsg(exception, request.getRequestURI());
    }

    /**
     * Handle bad request error msg.
     *
     * @param request   the request
     * @param exception the exception
     * @return the error msg
     */
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMsg handleBadRequest(HttpServletRequest request, Exception exception) {
        return new ErrorMsg(exception, request.getRequestURI());
    }

    /**
     * Handle internal server error error msg.
     *
     * @param request   the request
     * @param exception the exception
     * @return the error msg
     */
    @ExceptionHandler({IllegalArgumentException.class, DuplicateKeyException.class, NullPointerException.class, EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMsg handleInternalServerError(HttpServletRequest request, Exception exception) {
        return new ErrorMsg(exception, request.getRequestURI());
    }
}
