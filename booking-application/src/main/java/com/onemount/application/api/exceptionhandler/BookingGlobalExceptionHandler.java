package com.onemount.application.api.exceptionhandler;


import com.google.common.base.CaseFormat;
import com.onemount.application.api.response.BaseResponse;
import com.onemount.application.api.response.dto.FieldViolation;
import com.onemount.domain.exception.BookingErrors;
import com.onemount.domain.exception.BookingException;
import com.onemount.infrastructure.utils.CollectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for Rest API
 * It wraps all Booking Application exceptions to predefined structure that was defined in com/onemount/application/api/response/dto/BaseResponse.java
 * Not need to modify
 */
@ControllerAdvice
public class BookingGlobalExceptionHandler {

    @ExceptionHandler(BookingException.class)
    public ResponseEntity<BaseResponse<Void>> handleBusinessException(BookingException exception) {
        var data = BaseResponse.ofFailed(exception);
        return new ResponseEntity<>(data, exception.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var errors = CollectionUtils.toList(exception.getBindingResult().getFieldErrors(),
                e -> new FieldViolation(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.getField()), e.getDefaultMessage()));

        var errorCode = BookingErrors.INVALID_PARAMETERS;
        var data = BaseResponse.ofFailed(errorCode, "Invalid parameters of object: "
                + exception.getBindingResult().getObjectName(), errors);
        return new ResponseEntity<>(data, errorCode.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Void>> handleException(Exception exception) {
        var errorCode = BookingErrors.INTERNAL_SERVER_ERROR;
        var data = BaseResponse.ofFailed(errorCode, exception.getMessage());
        return new ResponseEntity<>(data, errorCode.getHttpStatus());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponse<Void>> handleIllegalArgumentException(IllegalArgumentException exception) {
        var errorCode = BookingErrors.INVALID_PARAMETERS;
        var data = BaseResponse.ofFailed(errorCode, exception.getMessage());
        return new ResponseEntity<>(data, errorCode.getHttpStatus());
    }
}

