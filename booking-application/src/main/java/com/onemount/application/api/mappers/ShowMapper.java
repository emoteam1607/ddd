package com.onemount.application.api.mappers;

import com.onemount.application.api.response.dto.ShowDto;
import com.onemount.application.api.response.dto.ShowPagingDto;
import com.onemount.domain.model.Show;
import com.onemount.infrastructure.commons.Page;
import com.onemount.infrastructure.utils.CollectionUtils;
import org.mapstruct.Mapper;

/**
 * Author: anct
 * Date: 10/09/2022
 * #YWNA
 */
@Mapper
public interface ShowMapper {

    ShowDto toDto(Show show);

    default ShowPagingDto toPagingDto(Page<Show> page) {
        var result = new ShowPagingDto();
        result.setTotal(page.getTotal());
        result.setPageIndex(page.getPageIndex());
        result.setPageSize(page.getPageSize());
        var showDtos = CollectionUtils.toList(page.getData(), this::toDto);
        result.setShows(showDtos);
        return result;
    }
}
