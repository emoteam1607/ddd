package com.onemount.infrastructure.repo;

import com.onemount.domain.model.enums.SeatStatus;
import com.onemount.infrastructure.repo.model.JpaSeat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

/**
 * author: anct
 * date: 10/9/2022
 * YNWA
 */
@Repository
public interface JpaSeatRepository extends JpaRepository<JpaSeat, Integer> {

    @Modifying
    @Query("update JpaSeat set status = :status, bookedDate = :bookedDate where showId = :showId and code = :seatCode")
    void updateSeatStatus(@Param("showId") Integer showId,
                          @Param("seatCode") String seatCode,
                          @Param("status") SeatStatus status,
                          @Param("bookedDate") LocalDate localDate);

    boolean existsByShowIdAndCodeAndStatus(Integer showId, String seatCode, SeatStatus status);

    Page<JpaSeat> findAllByShowId(Integer showId, Pageable pageable);

    Optional<JpaSeat> findByShowIdAndCode(Integer showId, String seatCode);
}
