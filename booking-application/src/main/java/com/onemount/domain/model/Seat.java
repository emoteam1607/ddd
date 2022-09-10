package com.onemount.domain.model;

import com.onemount.domain.model.enums.SeatStatus;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(of = "id")
public class Seat {

    private Integer id;

    private String showId;

    private String code;

    private SeatStatus status;

    private LocalDate bookedDate;
}
