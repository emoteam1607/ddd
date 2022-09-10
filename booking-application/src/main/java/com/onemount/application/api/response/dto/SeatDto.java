package com.onemount.application.api.response.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.onemount.domain.model.enums.SeatStatus;
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
public class SeatDto {
    private Integer id;

    private String showId;

    private String code;

    private SeatStatus status;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private LocalDate bookedDate;
}
