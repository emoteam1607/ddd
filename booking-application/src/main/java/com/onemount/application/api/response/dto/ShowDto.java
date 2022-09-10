package com.onemount.application.api.response.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ShowDto {

    @JsonProperty("show_id")
    private Integer id;

    private String name;

    private LocalDate startDate;

    private String imageUrl;
}
