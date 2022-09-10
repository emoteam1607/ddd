package com.onemount.application.api.request.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Author: anct
 * Date: 10/09/2022
 * #YWNA
 */
@Getter
@Setter
public class BaseSearchDto {

    private int page = 1;

    private int size = 100;
}
