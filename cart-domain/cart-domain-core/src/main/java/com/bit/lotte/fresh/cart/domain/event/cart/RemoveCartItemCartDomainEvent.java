package com.bit.lotte.fresh.cart.domain.event.cart;

import com.bit.lotte.fresh.cart.domain.entity.Cart;
import com.bit.lotte.fresh.cart.domain.entity.CartItem;
import com.bit.lotte.fresh.cart.domain.entity.Product;
import java.time.ZonedDateTime;

public class RemoveCartItemCartDomainEvent extends
    CartDomainEvent {


  public RemoveCartItemCartDomainEvent(Cart cart,
      CartItem cartItem,
      ZonedDateTime createdTime) {
    super(cart, cartItem, createdTime);
  }
}
