package com.onemount.domain.ports.api;

import com.onemount.domain.model.Seat;
import com.onemount.domain.model.Show;
import com.onemount.infrastructure.commons.Page;

/**
 * author: anct
 * date: 10/9/2022
 * YNWA
 */
public interface IShowService {

    Show getOrElseThrow(Integer showId);

    boolean existShow(Integer showId);

    Page<Show> getShows(int page, int size);

    Page<Seat> getSeats(Integer showId, int page, int size);
}
