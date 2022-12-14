package com.onemount.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * Booking Business Error Data Structure
 */
@Getter
@AllArgsConstructor
public class BookingBusinessError implements Serializable {
    private static final long serialVersionUID = 2405172041950251807L;
    private final int code;
    private final String message;
    private final HttpStatus httpStatus;
}
