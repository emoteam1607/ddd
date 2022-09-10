package com.onemount.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

/**
 * Author: anct
 * Date: 10/09/2022
 * #YWNA
 */
@Getter
@Setter
public class Show {

    private Integer id;

    private String name;

    private Set<Seat> seats;

    private LocalDate startDate;

    private String imageUrl;
}
