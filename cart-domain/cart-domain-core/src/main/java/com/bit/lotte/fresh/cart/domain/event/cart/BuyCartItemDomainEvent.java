package com.bit.lotte.fresh.cart.domain.event.cart;

import com.bit.lotte.fresh.cart.domain.entity.Cart;
import com.bit.lotte.fresh.cart.domain.entity.CartItem;
import com.bit.lotte.fresh.cart.domain.entity.Product;
import java.time.ZonedDateTime;

public class BuyCartItemDomainEvent extends
    CartDomainEvent {

  public BuyCartItemDomainEvent(Cart cart, CartItem cartItem,
      ZonedDateTime createdTime) {
    super(cart, cartItem, createdTime);
  }
}
