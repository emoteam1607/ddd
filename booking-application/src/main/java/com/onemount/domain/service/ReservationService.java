package com.onemount.domain.service;

import com.google.common.util.concurrent.Striped;
import com.onemount.domain.events.BookingAcceptedEvent;
import com.onemount.domain.exception.BookingErrors;
import com.onemount.domain.exception.BookingException;
import com.onemount.domain.model.Reservation;
import com.onemount.domain.model.enums.ReservationStatus;
import com.onemount.domain.model.enums.SeatStatus;
import com.onemount.domain.ports.api.IReservationService;
import com.onemount.domain.ports.spi.IGuestPersistence;
import com.onemount.domain.ports.spi.IReservationPersistence;
import com.onemount.domain.ports.spi.ISeatPersistence;
import com.onemount.domain.ports.spi.IShowPersistence;
import com.onemount.infrastructure.commons.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

/**
 * author: anct
 * date: 10/09/2022
 * YNWA
 */
@Slf4j
@Service
@SuppressWarnings("all")
@RequiredArgsConstructor
public class ReservationService implements IReservationService {

    private final IGuestPersistence guestPersistence;
    private final IShowPersistence showPersistence;
    private final IReservationPersistence reservationPersistence;
    private final ISeatPersistence seatPersistence;
    private final ApplicationEventPublisher eventPublisher;

    // using 2ˆ10 = 1024 locks.
    // Can using more when concurrent access heavily.
    private Striped<Lock> lockStriped = Striped.lazyWeakLock(10);

    @Override
    public Reservation getByCodeOrElseThrow(String reservationCode) {
        return reservationPersistence.findByCode(reservationCode)
                .orElseThrow(() -> new BookingException(BookingErrors.RESERVATION_NOT_FOUND));
    }

    @Override
    @Transactional
    public Reservation createReservation(Integer showId, Reservation reservation) {
        if (!showPersistence.existById(showId)) {
            throw new BookingException(BookingErrors.SHOW_NOT_FOUND);
        }
        return createReservation(reservation);
    }

    @Override
    @Transactional
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
        var lock = lockStriped.get(lockKey(reservation));
        try {
            lock.lock();

            var showId = reservation.getShowId();
            var seatCode = reservation.getSeatCode();

            var seatOp = seatPersistence.findByShowIdAndCode(showId, seatCode);

            if (seatOp.isEmpty()) {
                var msg = String.format("Seat %s not found. Please try other seat.", seatCode);
                throw new BookingException(BookingErrors.SEA_NOT_FOUND, msg);
            }

            var seat = seatOp.get();
            // check status of seat
            if (seat.getStatus() != SeatStatus.AVAILABLE) {
                var msg = String.format("Seat %s not available. Please try other seat.", seatCode);
                throw new BookingException(BookingErrors.SEAT_NOT_AVAILABLE, msg);
            }

            var bookedDate = LocalDate.now();
            // save the guest
            var savedGuest = guestPersistence.save(reservation.getGuest());
            reservation.setGuest(savedGuest);
            reservation.setBookedDate(bookedDate);

            // change seat status to BOOKED
            seat.setStatus(SeatStatus.BOOKED);
            seat.setBookedDate(bookedDate);

            var savedReservation = reservationPersistence.save(reservation);

            var savedSeat = seatPersistence.save(seat);

            // sent event here
            eventPublisher.publishEvent(new BookingAcceptedEvent("anct", savedReservation));

            log.info("DONE");

            return savedReservation;
        } finally {
            lock.unlock();
        }
    }

    /**
     * We lock on show id + seat code
     * Can using show id on lock.
     * to concurrency.
     */
    private String lockKey(Reservation reservation) {
        return String.format("%s_%s", reservation.getShowId(), reservation.getSeatCode());
    }

    @Override
    public Page<Reservation> getReservations(Integer showId, int pageIndex, int pageSize) {
        return reservationPersistence.findAllByShowId(showId, pageIndex, pageSize);
    }

    @Override
    @Transactional
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

        reservation.setStatus(ReservationStatus.CANCELED);
        reservation.setCanceledReason(canceledReason);
        reservation.setCanceledDate(LocalDate.now());

        // change seat status to AVAILABLE
        seatPersistence.refreshSeat(showId, reservation.getSeatCode());

        reservationPersistence.save(reservation);
    }
}
