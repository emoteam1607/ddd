package com.onemount.application.api.response.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.onemount.domain.model.enums.ReservationStatus;
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
public class ReservationDto {

    @JsonProperty("reservation_id")
    private Long id;

    private Integer showId;

    private String seatCode;

    // Booked user
    private GuestDto guest;

    private String canceledReason;

    private LocalDate canceledDate;

    private LocalDate bookedDate;

    private ReservationStatus status;

    public Long getBookedDate() {
        return bookedDate == null ? null : bookedDate.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
    }

    public Long getCanceledDate() {
        return canceledDate == null ? null : canceledDate.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
    }
}
