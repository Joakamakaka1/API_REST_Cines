package com.es.diecines.utils;

import org.springframework.stereotype.Component;

@Component
public class StringToLong {

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
