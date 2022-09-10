package com.onemount.application.api.request.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * author: anct
 * date: 10/9/2022
 * YNWA
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationCreateDto {

    @NotEmpty
    @JsonProperty("seat_codes")
    private List<String> selectedSeats;

    @Valid
    private ReservationCreateDto.GuestInfo user;

    @Validated
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GuestInfo {

        @NotBlank
        private String name;

        @NotBlank
        private String phoneNumber;
    }
}
