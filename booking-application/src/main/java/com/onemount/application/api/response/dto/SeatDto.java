package com.onemount.application.api.response.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.onemount.domain.model.enums.SeatStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.ZoneOffset;

/**
 * Author: anct
 * Date: 10/09/2022
 * #YWNA
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeatDto {
    private Integer id;

    private String showId;

    private String code;

    private SeatStatus status;

    private LocalDate bookedDate;

    public Long getBookedDate() {
        return bookedDate == null ? null : bookedDate.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
    }
}
