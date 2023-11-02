package com.bit.lotte.fresh.cart.domain.event.cart;

import com.bit.lotte.fresh.cart.common.domain.event.DomainEvent;
import com.bit.lotte.fresh.cart.domain.entity.Cart;
import com.bit.lotte.fresh.cart.domain.entity.CartItem;
import com.bit.lotte.fresh.cart.domain.entity.Product;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
abstract class CartDomainEvent implements DomainEvent<Cart> {

  private final Cart cart;
  private final CartItem cartItem;
  private final ZonedDateTime createdTime;

}
