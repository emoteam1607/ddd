package com.onemount.application.api.mappers;

import com.onemount.application.api.request.dto.ReservationCreateDto;
import com.onemount.domain.model.Reservation;
import com.onemount.domain.model.enums.ReservationStatus;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * author: anct
 * date: 10/9/2022
 * YNWA
 */
@Mapper
public interface ReservationCreateMapper {
    Reservation toModel(Integer showId, ReservationCreateDto data);

    Reservation toModel(Integer showId, String seatCode, ReservationCreateDto data);


    default List<Reservation> toModels(Integer showId, ReservationCreateDto data) {
        var reservations = new ArrayList<Reservation>(data.getSelectedSeats().size());

        for (var seatCode : data.getSelectedSeats()) {
            var reservation = toModel(showId, seatCode, data);
            reservation.setStatus(ReservationStatus.ACCEPTED);
            reservations.add(reservation);
        }

        return reservations;
    }
}
