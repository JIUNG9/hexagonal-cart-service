package com.bit.lotte.fresh.cart.common.domain.event.publisher;

import com.bit.lotte.fresh.cart.common.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent<T>> {

    void publish(T domainEvent);
}
