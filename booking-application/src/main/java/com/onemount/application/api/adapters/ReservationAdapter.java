package com.onemount.application.api.adapters;

import com.onemount.application.api.mappers.ReservationCreateMapper;
import com.onemount.application.api.mappers.ReservationMapper;
import com.onemount.application.api.request.dto.BaseSearchDto;
import com.onemount.application.api.request.dto.ReservationCancelDto;
import com.onemount.application.api.request.dto.ReservationCreateDto;
import com.onemount.application.api.response.dto.ReservationDto;
import com.onemount.application.api.response.dto.ReservationPagingDto;
import com.onemount.domain.ports.api.IReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * author: anct
 * date: 10/9/2022
 * YNWA
 */
@Component
@RequiredArgsConstructor
public class ReservationAdapter {

    private final ReservationMapper reservationMapper;
    private final IReservationService reservationService;
    private final ReservationCreateMapper reservationCreateMapper;

    public ReservationDto createReservation(Integer showId, ReservationCreateDto data) {
        var createdReservation = reservationService.createReservation(showId, reservationCreateMapper.toModel(showId, data));
        return reservationMapper.toDto(createdReservation);
    }

    public ReservationPagingDto createReservations(Integer showId, ReservationCreateDto data) {
        var createdReservations = reservationService.createReservations(showId, reservationCreateMapper.toModels(showId, data));
        return reservationMapper.toPagingDto(createdReservations);
    }

    public void cancelReservation(Integer showId,
                                  String reservationCode,
                                  ReservationCancelDto data) {
        reservationService.cancelReservation(showId, reservationCode, data.getCanceledReason(), data.getCanceledDate());
    }

    public ReservationPagingDto getReservations(Integer showId, BaseSearchDto searchDto) {
        var result = reservationService.getReservations(showId, searchDto.getPage(), searchDto.getSize());
        return reservationMapper.toPagingDto(result);
    }
}
