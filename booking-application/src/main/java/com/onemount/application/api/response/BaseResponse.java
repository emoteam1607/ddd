package com.onemount.application.api.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.onemount.application.api.response.dto.FieldViolation;
import com.onemount.domain.exception.BookingBusinessError;
import com.onemount.domain.exception.BookingException;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Base Response for RestAPI
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {
    private T data;
    private Metadata meta = new Metadata();

    public static <T> BaseResponse<T> ofSucceeded(T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.data = data;
        response.meta.code = HttpStatus.OK.value();
        response.meta.message = "OK";
        return response;
    }

    public static <T> BaseResponse<T> ofSucceeded() {
        BaseResponse<T> response = new BaseResponse<>();
        response.meta.code = HttpStatus.OK.value();
        return response;
    }

    public static BaseResponse<Void> ofFailed(BookingBusinessError errorCode) {
        return ofFailed(errorCode, null);
    }

    public static BaseResponse<Void> ofFailed(BookingBusinessError errorCode, String message) {
        return ofFailed(errorCode, message, null);
    }

    public static BaseResponse<Void> ofFailed(BookingBusinessError errorCode, String message, List<FieldViolation> errors) {
        BaseResponse<Void> response = new BaseResponse<>();
        response.meta.code = errorCode.getCode();
        response.meta.message = (message != null) ? message : errorCode.getMessage();
        response.meta.errors = (errors != null) ? new ArrayList<>(errors) : null;
        return response;
    }

    public static BaseResponse<Void> ofFailed(BookingException exception) {
        return ofFailed(exception.getErrorCode(), exception.getMessage());
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Metadata {
        private Integer code;
        private List<FieldViolation> errors;
        private String message;
    }
}
