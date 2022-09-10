package com.onemount.domain.events;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * Author: anct
 * Date: 10/09/2022
 * #YWNA
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseEvent extends ApplicationEvent {
    private String id;
    private String event;
    private Object payload;

    public BaseEvent(Object source,
                     String id,
                     String event,
                     Object payload) {
        super(source);
        this.id = id;
        this.event = event;
        this.payload = payload;
    }
}
