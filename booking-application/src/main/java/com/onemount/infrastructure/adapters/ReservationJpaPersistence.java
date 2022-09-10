package com.onemount.infrastructure.adapters;

import com.onemount.domain.model.Reservation;
import com.onemount.domain.ports.spi.IReservationPersistence;
import com.onemount.infrastructure.commons.Page;
import com.onemount.infrastructure.repo.JpaReservationRepository;
import com.onemount.infrastructure.repo.mappers.JpaReservationMapper;
import com.onemount.infrastructure.repo.model.JpaReservation;
import com.onemount.infrastructure.utils.CollectionUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * author: anct
 * date: 10/9/2022
 * YNWA
 */
@Component
public class ReservationJpaPersistence
        extends JpaPersistence<Reservation, JpaReservation, Long, JpaReservationRepository> implements IReservationPersistence {

    public ReservationJpaPersistence(JpaReservationRepository repo,
                                     JpaReservationMapper mapper) {
        super(repo, mapper);
    }

    @Override
    public Optional<Reservation> findByCode(String code) {
        var op = repo.findByCode(code);
        return op.map(mapper::toDomainModel);
    }

    @Override
    public Page<Reservation> findAllByShowId(Integer showId, int pageIndex, int pageSize) {
        var realPageIndex = Math.max(0, pageIndex - 1);
        var page = repo.findAllByShowId(showId, PageRequest.of(realPageIndex, pageSize));
        var result = CollectionUtils.toList(page.getContent(), mapper::toDomainModel);
        return new Page<>(page.getTotalElements(), pageIndex, pageSize, result);
    }
}
