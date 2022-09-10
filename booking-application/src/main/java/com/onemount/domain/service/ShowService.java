package com.onemount.domain.service;

import com.onemount.domain.exception.BookingErrors;
import com.onemount.domain.exception.BookingException;
import com.onemount.domain.model.Seat;
import com.onemount.domain.model.Show;
import com.onemount.domain.ports.api.IShowService;
import com.onemount.domain.ports.spi.ISeatPersistence;
import com.onemount.domain.ports.spi.IShowPersistence;
import com.onemount.infrastructure.commons.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * author: anct
 * date: 10/9/2022
 * YNWA
 */
@Service
@RequiredArgsConstructor
public class ShowService implements IShowService {

    private final IShowPersistence showPersistence;
    private final ISeatPersistence seatPersistence;

    @Override
    public Show getOrElseThrow(Integer showId) {
        return showPersistence.findById(showId)
                .orElseThrow(() -> new BookingException(BookingErrors.SHOW_NOT_FOUND));
    }

    @Override
    public boolean existShow(Integer showId) {
        return showPersistence.existById(showId);
    }

    @Override
    public Page<Show> getShows(int pageIndex, int pageSize) {
        var result = showPersistence.findAll(pageIndex, pageSize);
        return new Page<>(result.getTotal(), pageIndex, pageSize, result.getData());
    }

    @Override
    public Page<Seat> getSeats(Integer showId, int pageIndex, int pageSize) {

        if (!showPersistence.existById(showId)) {
            throw new BookingException(BookingErrors.SHOW_NOT_FOUND);
        }

        return seatPersistence.findAllByShowId(showId, pageIndex, pageSize);
    }
}
