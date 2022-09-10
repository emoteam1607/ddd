package com.onemount.domain.ports.spi;

import com.onemount.domain.model.Reservation;
import com.onemount.infrastructure.commons.Page;

import java.util.Optional;

/**
 * author: anct
 * date: 10/9/2022
 * YNWA
 */
public interface IReservationPersistence extends BasePersistence<Reservation, Long> {

    Optional<Reservation> findByCode(String code);

    Page<Reservation> findAllByShowId(Integer showId, int pageIndex, int pageSize);
}
