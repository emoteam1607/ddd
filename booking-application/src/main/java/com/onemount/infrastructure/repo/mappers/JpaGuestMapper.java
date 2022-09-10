package com.onemount.infrastructure.repo.mappers;

import com.onemount.domain.model.Guest;
import com.onemount.infrastructure.repo.model.JpaGuest;
import org.mapstruct.Mapper;

/**
 * author: anct
 * date: 10/9/2022
 * YNWA
 */
@Mapper
public interface JpaGuestMapper extends BaseMapper<Guest, JpaGuest> {
}
