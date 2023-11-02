package com.bit.lotte.fresh.cart.service.dto.response;

import com.bit.lotte.fresh.cart.common.domain.valueobject.ProductId;
import com.bit.lotte.fresh.cart.domain.valueobject.Province;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class RemoveCartProductResponse {

  private Long productId;
  private Province province;
  private String productName;
  private String message;


  @JsonCreator
  public RemoveCartProductResponse(
      @JsonProperty("productId") Long productId,
      Province province,
      String productName) {
    this.productId = productId;
    this.province = province;
    this.productName = productName;
    setInitMessage();
  }

  private void setInitMessage() {
    StringBuilder sb = new StringBuilder();
    sb.append(province);
    sb.append("의");
    sb.append(productName);
    sb.append("가 삭제됩니다.");
    message = sb.toString();
  }
}
