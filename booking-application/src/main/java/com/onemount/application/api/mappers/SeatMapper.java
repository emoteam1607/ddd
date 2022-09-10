package com.onemount.application.api.mappers;

import com.onemount.application.api.response.dto.SeatDto;
import com.onemount.application.api.response.dto.SeatPagingDto;
import com.onemount.domain.model.Seat;
import com.onemount.infrastructure.commons.Page;
import com.onemount.infrastructure.utils.CollectionUtils;
import org.mapstruct.Mapper;

/**
 * Author: anct
 * Date: 10/09/2022
 * #YWNA
 */
@Mapper
public interface SeatMapper {

    SeatDto toDto(Seat seat);

    default SeatPagingDto toPagingDto(Page<Seat> page) {
        var result = new SeatPagingDto();
        result.setTotal(page.getTotal());
        result.setPageIndex(page.getPageIndex());
        result.setPageSize(page.getPageSize());
        var seatDtos = CollectionUtils.toList(page.getData(), this::toDto);
        result.setSeatList(seatDtos);
        return result;
    }
}
