package com.onemount.application.api.mappers;

import com.onemount.application.api.response.dto.ReservationDto;
import com.onemount.application.api.response.dto.ReservationPagingDto;
import com.onemount.domain.model.Reservation;
import com.onemount.infrastructure.commons.Page;
import com.onemount.infrastructure.utils.CollectionUtils;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Author: anct
 * Date: 10/09/2022
 * #YWNA
 */
@Mapper
public interface ReservationMapper {

    ReservationDto toDto(Reservation reservation);

    default ReservationPagingDto toPagingDto(Page<Reservation> page) {
        var result = new ReservationPagingDto();
        result.setTotal(page.getTotal());
        result.setPageIndex(page.getPageIndex());
        result.setPageSize(page.getPageSize());
        var reservationDtos = CollectionUtils.toList(page.getData(), this::toDto);
        result.setReservations(reservationDtos);
        return result;
    }

    default ReservationPagingDto toPagingDto(List<Reservation> reservations) {
        var result = new ReservationPagingDto();
        result.setTotal((long) reservations.size());
        var reservationDtos = CollectionUtils.toList(reservations, this::toDto);
        result.setReservations(reservationDtos);
        return result;
    }
}
