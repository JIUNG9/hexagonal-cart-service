package com.bit.lotte.fresh.cart.service.dto.response;

import com.bit.lotte.fresh.cart.common.domain.valueobject.ProductId;
import com.bit.lotte.fresh.cart.common.domain.valueobject.UserCartId;
import com.bit.lotte.fresh.cart.domain.entity.CartItem;
import com.bit.lotte.fresh.cart.domain.valueobject.Province;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CartItemInfoResponse {

  private UserCartId cartId;
  private CartItem cartItem;

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("유저 아이디: ");
    sb.append(cartId);
    sb.append("카트 제품: ");
    sb.append(cartItem);
    return sb.toString();
  }
}
