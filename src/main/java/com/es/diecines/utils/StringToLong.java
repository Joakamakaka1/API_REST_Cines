package com.es.diecines.utils;

import com.es.diecines.errores.BdException;
import org.springframework.stereotype.Component;

/**
 * The type String to long.
 */
@Component
public class StringToLong {
    /**
     * String to long.
     *
     * @param id the id
     * @return the long
     */
    public static Long stringToLong(String id) {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new BdException("El ID proporcionado no tiene un formato válido: " + id, e);
        }
    }
}
