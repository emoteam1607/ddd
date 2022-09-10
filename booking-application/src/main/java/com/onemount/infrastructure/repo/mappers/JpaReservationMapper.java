package com.onemount.infrastructure.repo.mappers;

import com.onemount.domain.model.Reservation;
import com.onemount.infrastructure.repo.model.JpaReservation;
import org.mapstruct.Mapper;

/**
 * author: anct
 * date: 10/9/2022
 * YNWA
 */
@Mapper
public interface JpaReservationMapper extends BaseMapper<Reservation, JpaReservation> {
}
