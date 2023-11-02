package com.bit.lotte.fresh.cart.service.dto.response;

import com.bit.lotte.fresh.cart.common.domain.valueobject.ProductId;
import com.bit.lotte.fresh.cart.domain.entity.Cart;
import com.bit.lotte.fresh.cart.domain.entity.CartItem;
import com.bit.lotte.fresh.cart.domain.valueobject.Province;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class AddProductInCartResponse {

  ProductId productId;
  Province province;
  String productName;
  private String message;

  public AddProductInCartResponse(
      ProductId productId, Province province, String productName) {
    this.productId = productId;
    this.province = province;
    this.productName = productName;
    setInitMessage();
  }

  private void setInitMessage() {
    StringBuilder sb = new StringBuilder();
    sb.append(productName);
    sb.append("가 추가되었습니다.");
    message = sb.toString();
  }

}
