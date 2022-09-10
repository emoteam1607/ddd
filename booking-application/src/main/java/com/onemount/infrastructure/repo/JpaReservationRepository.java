package com.onemount.infrastructure.repo;

import com.onemount.infrastructure.repo.model.JpaReservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaReservationRepository extends JpaRepository<JpaReservation, Long> {

    Optional<JpaReservation> findByCode(String code);

    Page<JpaReservation> findAllByShowId(Integer showId, Pageable pageable);
}
