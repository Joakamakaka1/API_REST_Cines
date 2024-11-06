package com.es.diecines.utils;

import org.springframework.stereotype.Component;

/**
 * The type String to long.
 */
@Component
public class StringToLong {
    /**
     * String to long long.
     *
     * @param id the id
     * @return the long
     */
    public static Long stringToLong(String id) {
        Long idLong = 0L;
        try {
            idLong = Long.parseLong(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return idLong;
    }
}
