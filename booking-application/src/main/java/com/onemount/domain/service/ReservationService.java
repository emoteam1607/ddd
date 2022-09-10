package com.onemount.domain.service;

import com.onemount.domain.exception.BookingErrors;
import com.onemount.domain.exception.BookingException;
import com.onemount.domain.model.Reservation;
import com.onemount.domain.model.enums.ReservationStatus;
import com.onemount.domain.ports.api.IReservationService;
import com.onemount.domain.ports.spi.IGuestPersistence;
import com.onemount.domain.ports.spi.IReservationPersistence;
import com.onemount.domain.ports.spi.IShowPersistence;
import com.onemount.infrastructure.commons.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * author: anct
 * date: 10/09/2022
 * YNWA
 */
@Service
@RequiredArgsConstructor
public class ReservationService implements IReservationService {

    private final IGuestPersistence guestPersistence;
    private final IShowPersistence showPersistence;
    private final IReservationPersistence reservationPersistence;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Reservation getByCodeOrElseThrow(String reservationCode) {
        return reservationPersistence.findByCode(reservationCode)
                .orElseThrow(() -> new BookingException(BookingErrors.RESERVATION_NOT_FOUND));
    }

    @Override
    public Reservation createReservation(Integer showId, Reservation reservation) {

        if (!showPersistence.existById(showId)) {
            throw new BookingException(BookingErrors.SHOW_NOT_FOUND);
        }

        //todo handle concurrent here

        return createReservation(reservation);
    }

    @Override
    public List<Reservation> createReservations(Integer showId, List<Reservation> reservations) {

        if (!showPersistence.existById(showId)) {
            throw new BookingException(BookingErrors.SHOW_NOT_FOUND);
        }
        var results = new ArrayList<Reservation>(reservations.size());

        for (var reservation : reservations) {
            results.add(createReservation(reservation));
        }
        return results;
    }

    private Reservation createReservation(Reservation reservation) {
        var savedGuest = guestPersistence.save(reservation.getGuest());
        reservation.setGuest(savedGuest);

        // save guests.
        // create reservation.

        var savedReservation = reservationPersistence.save(reservation);

        // sent event here

        return savedReservation;
    }

    @Override
    public Page<Reservation> getReservations(Integer showId, int pageIndex, int pageSize) {
        return reservationPersistence.findAllByShowId(showId, pageIndex, pageSize);
    }

    @Override
    public void cancelReservation(Integer showId,
                                  String reservationCode,
                                  String canceledReason,
                                  LocalDate canceledDate) {
        if (!showPersistence.existById(showId)) {
            throw new BookingException(BookingErrors.SHOW_NOT_FOUND);
        }

        var reservation = getByCodeOrElseThrow(reservationCode);

        if (reservation.getStatus() == ReservationStatus.CANCELED) {
            throw new BookingException(BookingErrors.RESERVATION_ALREADY_CANCELED);
        }

        reservation.setCanceledReason(canceledReason);
        reservation.setStatus(ReservationStatus.CANCELED);
        reservation.setCanceledDate(LocalDate.now());

        reservationPersistence.save(reservation);
    }
}
