package com.onemount.domain.model;

import com.onemount.domain.model.enums.ReservationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

/**
 * author: anct
 * date: 10/9/2022
 * YNWA
 */
@Getter
@Setter
public class Reservation {

    private Long id;

    private Integer showId;

    private String seatCode;

    // auto generate
    private String code = UUID.randomUUID().toString();

    // Booked user
    private Guest guest;

    private String canceledReason;

    private LocalDate canceledDate;

    private LocalDate bookedDate;

    private ReservationStatus status;
}
