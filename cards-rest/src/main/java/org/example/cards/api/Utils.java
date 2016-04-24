package org.example.cards.api;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Utils {
    public static <T> ResponseEntity<T> asResponse(T obj) {
        HttpStatus httpStatus;
        if (obj != null || (obj instanceof Collection && ((Collection) obj).isEmpty())) {
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<T>(obj, httpStatus);
    }
}
