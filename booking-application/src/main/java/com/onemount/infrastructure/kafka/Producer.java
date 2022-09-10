package com.onemount.infrastructure.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onemount.domain.events.BookingAcceptedEvent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * Author: anct
 * Date: 10/09/2022
 * #YWNA
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class Producer {

    // change to config here
    private static final String TOPIC = "om-hack2hire-booking-accepted";

    private final ObjectMapper mapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Handle send notification.
     */
    @Async
    @SneakyThrows
    @EventListener
    public void notify(BookingAcceptedEvent event) {
        try {
            var future = kafkaTemplate.send(TOPIC, mapper.writeValueAsString(event));

            future.addCallback(new ListenableFutureCallback<>() {
                @Override
                public void onFailure(@NonNull Throwable throwable) {
                    log.error(throwable.getMessage(), throwable);
                }

                @Override
                public void onSuccess(SendResult<String, String> result) {
                    log.info("Sent event success {}", result);
                }
            });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
