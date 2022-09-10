package com.onemount.domain.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception Dictionary
 * You should declare all Booking Application exceptions in here
 */
public class BookingErrors {


    /**
     * 400
     */
    public static final BookingBusinessError RESERVATION_ALREADY_CANCELED = new BookingBusinessError(400, "Your reservation already canceled", HttpStatus.BAD_REQUEST);
    public static final BookingBusinessError INVALID_PARAMETERS = new BookingBusinessError(400, "Invalid parameters", HttpStatus.BAD_REQUEST);


    /**
     * 403
     */
    public static final BookingBusinessError FORBIDDEN_ERROR = new BookingBusinessError(403, "You don not have any permissions to access this resource", HttpStatus.FORBIDDEN);
    /**
     * 404
     */
    public static final BookingBusinessError SHOW_NOT_FOUND = new BookingBusinessError(404, "Your selected show not existed", HttpStatus.NOT_FOUND);
    public static final BookingBusinessError RESERVATION_NOT_FOUND = new BookingBusinessError(404, "Your selected reservation not existed", HttpStatus.NOT_FOUND);

    /**
     * 500
     */
    public static final BookingBusinessError INTERNAL_SERVER_ERROR = new BookingBusinessError(500, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);


    private BookingErrors() {
    }


}
