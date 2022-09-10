package com.onemount.application.api.response.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShowDto {

    @JsonProperty("show_id")
    private Integer id;

    private String name;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private LocalDate startDate;

    private String imageUrl;
}
