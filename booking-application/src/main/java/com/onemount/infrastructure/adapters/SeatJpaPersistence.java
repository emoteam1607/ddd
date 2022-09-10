package com.onemount.infrastructure.adapters;

import com.onemount.domain.model.Seat;
import com.onemount.domain.model.enums.SeatStatus;
import com.onemount.domain.ports.spi.ISeatPersistence;
import com.onemount.infrastructure.commons.Page;
import com.onemount.infrastructure.repo.JpaSeatRepository;
import com.onemount.infrastructure.repo.mappers.JpaSeatMapper;
import com.onemount.infrastructure.repo.model.JpaSeat;
import com.onemount.infrastructure.utils.CollectionUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

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
        var page = repo.findAllByShowId(showId, PageRequest.of(Math.max(0, pageIndex - 1), pageSize));
        var result = CollectionUtils.toList(page.getContent(), mapper::toDomainModel);
        return new Page<>(page.getTotalElements(), pageIndex, pageSize, result);
    }

    @Override
    public void updateStatus(Integer showId,
                             String seatCode,
                             LocalDate bookedDate,
                             SeatStatus status) {
        repo.updateSeatStatus(showId, seatCode, status, bookedDate);
    }

    @Override
    public void refreshSeat(Integer showId, String seatCode) {
        repo.updateSeatStatus(showId, seatCode, SeatStatus.AVAILABLE, null);
    }

    @Override
    public boolean isSeatAvailable(Integer showId, String seatCode) {
        return repo.existsByShowIdAndCodeAndStatus(showId, seatCode, SeatStatus.AVAILABLE);
    }

    @Override
    public Optional<Seat> findByShowIdAndCode(Integer showId, String seatCode) {
        var op = repo.findByShowIdAndCode(showId, seatCode);
        return op.map(mapper::toDomainModel);
    }
}
