package com.onemount.domain.ports.api;

import com.onemount.domain.model.Reservation;
import com.onemount.infrastructure.commons.Page;

import java.time.LocalDate;
import java.util.List;

/**
 * author: anct
 * date: 10/9/2022
 * YNWA
 */
public interface IReservationService {

    Reservation getByCodeOrElseThrow(String reservationCode);

    Reservation createReservation(Integer showId, Reservation reservation);

    Page<Reservation> getReservations(Integer showId, int pageIndex, int pageSize);

    void cancelReservation(Integer showId,
                           String reservationCode,
                           String canceledReason,
                           LocalDate canceledDate);

    List<Reservation> createReservations(Integer showId, List<Reservation> reservations);
}
