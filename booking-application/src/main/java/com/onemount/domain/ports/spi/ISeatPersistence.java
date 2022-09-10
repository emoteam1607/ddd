package com.onemount.domain.ports.spi;

import com.onemount.domain.model.Seat;
import com.onemount.domain.model.enums.SeatStatus;
import com.onemount.infrastructure.commons.Page;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Author: anct
 * Date: 10/09/2022
 * #YWNA
 */
public interface ISeatPersistence extends BasePersistence<Seat, Integer> {

    Page<Seat> findAllByShowId(Integer showId, int pageIndex, int pageSize);

    void updateStatus(Integer showId, String seatCode, LocalDate bookedDate, SeatStatus status);

    void refreshSeat(Integer showId, String seatCode);

    boolean isSeatAvailable(Integer showId, String seatCode);

    Optional<Seat> findByShowIdAndCode(Integer showId, String showCode);
}
