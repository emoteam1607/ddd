package com.onemount.infrastructure.adapters;

import com.onemount.domain.model.Seat;
import com.onemount.domain.ports.spi.ISeatPersistence;
import com.onemount.infrastructure.commons.Page;
import com.onemount.infrastructure.repo.JpaSeatRepository;
import com.onemount.infrastructure.repo.mappers.JpaSeatMapper;
import com.onemount.infrastructure.repo.model.JpaSeat;
import org.springframework.stereotype.Component;

/**
 * Author: anct
 * Date: 10/09/2022
 * #YWNA
 */
@Component
public class SeatJpaPersistence extends JpaPersistence<Seat, JpaSeat, Integer, JpaSeatRepository> implements ISeatPersistence {

    public SeatJpaPersistence(JpaSeatRepository repo,
                              JpaSeatMapper mapper) {
        super(repo, mapper);
    }

    @Override
    public Page<Seat> findAllByShowId(Integer showId, int pageIndex, int pageSize) {
        return null;
    }
}
