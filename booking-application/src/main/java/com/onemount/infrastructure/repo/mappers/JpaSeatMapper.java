package com.onemount.infrastructure.repo.mappers;

import com.onemount.domain.model.Seat;
import com.onemount.infrastructure.repo.model.JpaSeat;
import org.mapstruct.Mapper;

/**
 * author: anct
 * date: 10/9/2022
 * YNWA
 */
@Mapper
public interface JpaSeatMapper extends BaseMapper<Seat, JpaSeat> {
}
