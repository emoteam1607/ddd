package com.onemount.domain.ports.spi;

import com.onemount.domain.model.Seat;
import com.onemount.infrastructure.commons.Page;

/**
 * Author: anct
 * Date: 10/09/2022
 * #YWNA
 */
public interface ISeatPersistence extends BasePersistence<Seat, Integer> {

    Page<Seat> findAllByShowId(Integer showId, int pageIndex, int pageSize);
}
