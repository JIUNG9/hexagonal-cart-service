package com.bit.lotte.fresh.cart.service.port.output;

import com.bit.lotte.fresh.cart.domain.event.cart.BuyCartItemDomainEvent;

public interface BuyCartItemPublisher {
  public void publish(BuyCartItemDomainEvent buyCartItemDomainEvent);
}
