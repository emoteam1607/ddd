package com.onemount.application.api.controller;

import com.onemount.application.api.adapters.ShowAdapter;
import com.onemount.application.api.request.dto.BaseSearchDto;
import com.onemount.application.api.response.BaseResponse;
import com.onemount.application.api.response.dto.SeatPagingDto;
import com.onemount.application.api.response.dto.ShowPagingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: anct
 * Date: 10/09/2022
 * #YWNA
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/shows")
public class ShowController {

    private final ShowAdapter showAdapter;

    @GetMapping
    public BaseResponse<ShowPagingDto> getShows(@ModelAttribute BaseSearchDto searchDto) {
        return BaseResponse.ofSucceeded(showAdapter.getShows(searchDto));
    }

    @GetMapping("/{showId}/seats")
    public BaseResponse<SeatPagingDto> getSeats(@PathVariable Integer showId,
                                                @ModelAttribute BaseSearchDto searchDto) {
        return BaseResponse.ofSucceeded(showAdapter.getSeats(showId, searchDto));
    }
}
