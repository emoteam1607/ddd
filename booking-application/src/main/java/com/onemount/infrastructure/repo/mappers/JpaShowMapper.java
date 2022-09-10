package com.onemount.infrastructure.repo.mappers;

import com.onemount.domain.model.Show;
import com.onemount.infrastructure.repo.model.JpaShow;
import org.mapstruct.Mapper;

/**
 * author: anct
 * date: 10/9/2022
 * YNWA
 */
@Mapper
public interface JpaShowMapper extends BaseMapper<Show, JpaShow> {
}
