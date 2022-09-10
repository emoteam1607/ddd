package com.onemount.application.api.response.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasePagingDto {
    @JsonProperty("page")
    private Integer pageIndex;

    @JsonProperty("size")
    private Integer pageSize;

    private Long total;
}