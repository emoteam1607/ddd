package com.onemount.application.api.controller;

import com.onemount.application.api.adapters.ReservationAdapter;
import com.onemount.application.api.request.dto.BaseSearchDto;
import com.onemount.application.api.request.dto.ReservationCancelDto;
import com.onemount.application.api.request.dto.ReservationCreateDto;
import com.onemount.application.api.response.BaseResponse;
import com.onemount.application.api.response.dto.ReservationPagingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shows/{showId}/reservations")
public class ReservationController {

    private final ReservationAdapter reservationAdapter;

    @PostMapping
    public BaseResponse<ReservationPagingDto> createReservation(@PathVariable Integer showId,
                                                                @Valid @RequestBody ReservationCreateDto data) {
        return BaseResponse.ofSucceeded(reservationAdapter.createReservations(showId, data));
    }

    @PutMapping("/{reservationCode}")
    public BaseResponse<Void> cancelReservation(@PathVariable("showId") Integer showId,
                                                @PathVariable("reservationCode") String reservationCode,
                                                @Valid @RequestBody ReservationCancelDto data) {
        reservationAdapter.cancelReservation(showId, reservationCode, data);
        return BaseResponse.ofSucceeded();
    }

    @GetMapping
    public BaseResponse<ReservationPagingDto> getReservations(@PathVariable Integer showId,
                                                              @ModelAttribute BaseSearchDto searchDto) {
        return BaseResponse.ofSucceeded(reservationAdapter.getReservations(showId, searchDto));
    }
}