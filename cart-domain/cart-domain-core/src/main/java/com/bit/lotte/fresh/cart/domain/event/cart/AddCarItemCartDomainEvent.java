package com.bit.lotte.fresh.cart.domain.event.cart;

import com.bit.lotte.fresh.cart.domain.entity.Cart;
import com.bit.lotte.fresh.cart.domain.entity.CartItem;
import com.bit.lotte.fresh.cart.domain.entity.Product;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;

public class AddCarItemCartDomainEvent extends
    CartDomainEvent {

  public AddCarItemCartDomainEvent(Cart cart, CartItem cartItem, ZonedDateTime createdTime) {
    super(cart, cartItem, createdTime);
  }
}
