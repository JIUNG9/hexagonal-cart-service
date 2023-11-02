package com.bit.lotte.fresh.cart.domain.event.cart;

import com.bit.lotte.fresh.cart.domain.entity.Cart;
import com.bit.lotte.fresh.cart.domain.entity.CartItem;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
public class GetMyCartItemEvent extends CartDomainEvent{

  public GetMyCartItemEvent(Cart cart,
      CartItem cartItem, ZonedDateTime createdTime) {
    super(cart, cartItem, createdTime);
  }
}
