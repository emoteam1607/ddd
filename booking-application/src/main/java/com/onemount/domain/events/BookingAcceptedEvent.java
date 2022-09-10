package com.onemount.domain.events;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Author: anct
 * Date: 10/09/2022
 * #YWNA
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingAcceptedEvent extends BaseEvent {

    public BookingAcceptedEvent(Object source, Object payload) {
        super(source, UUID.randomUUID().toString(), "BookingAcceptedEvent", payload);
    }
}
