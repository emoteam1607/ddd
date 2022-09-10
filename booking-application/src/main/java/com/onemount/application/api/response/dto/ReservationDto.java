package com.onemount.application.api.response.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.onemount.domain.model.enums.ReservationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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
    private GuestDto user;

    private String canceledReason;

    private LocalDate canceledDate;

    private LocalDate bookedDate;

    private ReservationStatus status;
}
