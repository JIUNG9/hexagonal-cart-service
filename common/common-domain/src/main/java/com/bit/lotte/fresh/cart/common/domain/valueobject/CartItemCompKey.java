package com.bit.lotte.fresh.cart.common.domain.valueobject;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartItemCompKey  {

  private ProductId productId;
  private UserCartId userCartId;


  @JsonCreator
  public CartItemCompKey(
      ProductId productId, UserCartId userCartId) {
    this.productId = productId;
    this.userCartId = userCartId;
  }
}
