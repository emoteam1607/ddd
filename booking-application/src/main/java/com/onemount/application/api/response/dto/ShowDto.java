package com.onemount.application.api.response.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ShowDto {

    @JsonProperty("show_id")
    private Integer id;

    private String name;

    private LocalDate startDate;

    private String imageUrl;

    public Long getStartDate() {
        return startDate == null ? null : startDate.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
    }
}
