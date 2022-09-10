package com.onemount.infrastructure.kafka;

public interface KafkaEventHandler<E> {

    /**
     * Check if event match handler.
     *
     * @param event event.
     */
    default boolean shouldHandle(E event) {
        return true;
    }

    /**
     * Implement biz logic here.
     *
     * @param event event.
     */
    void handle(E event);
}
