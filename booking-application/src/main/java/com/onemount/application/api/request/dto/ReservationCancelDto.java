package com.onemount.application.api.request.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.onemount.domain.model.enums.ReservationStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Author: anct
 * Date: 10/09/2022
 * #YWNA
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationCancelDto {

    @JsonIgnore
    private ReservationStatus status = ReservationStatus.CANCELED;

    @NotNull
    private LocalDate canceledDate;

    @NotBlank
    private String canceledReason;
}
