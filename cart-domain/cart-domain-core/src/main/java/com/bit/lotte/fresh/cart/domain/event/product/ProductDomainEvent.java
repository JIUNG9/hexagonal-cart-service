package com.bit.lotte.fresh.cart.domain.event.product;

import com.bit.lotte.fresh.cart.common.domain.event.DomainEvent;
import com.bit.lotte.fresh.cart.domain.entity.Product;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
abstract class ProductDomainEvent implements DomainEvent<Product> {
  private final Product product;
  private final ZonedDateTime createdTime;
}
