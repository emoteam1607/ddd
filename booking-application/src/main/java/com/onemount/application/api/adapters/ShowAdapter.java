package com.onemount.application.api.adapters;

import com.onemount.application.api.mappers.SeatMapper;
import com.onemount.application.api.mappers.ShowMapper;
import com.onemount.application.api.request.dto.BaseSearchDto;
import com.onemount.application.api.response.dto.SeatPagingDto;
import com.onemount.application.api.response.dto.ShowPagingDto;
import com.onemount.domain.ports.api.IShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Author: anct
 * Date: 10/09/2022
 * #YWNA
 */
@Component
@RequiredArgsConstructor
public class ShowAdapter {
    private final IShowService showService;
    private final ShowMapper showMapper;
    private final SeatMapper seatMapper;

    public ShowPagingDto getShows(BaseSearchDto searchDto) {
        var result = showService.getShows(searchDto.getPage(), searchDto.getSize());
        return showMapper.toPagingDto(result);
    }

    public SeatPagingDto getSeats(Integer showId, BaseSearchDto searchDto) {
        var result = showService.getSeats(showId, searchDto.getPage(), searchDto.getSize());
        return seatMapper.toPagingDto(result);
    }
}
