package com.bit.lotte.fresh.cart.service.dto.response;

import com.bit.lotte.fresh.cart.common.domain.valueobject.ProductId;
import com.bit.lotte.fresh.cart.domain.valueobject.Province;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class BuyProductInCartResponse {

  @NotNull
  private Long productId;
  private Province province;
  private String productName;
  private int selectedQuantity;
  private String message;

  public BuyProductInCartResponse(
      Long productId, Province province, String productName, int selectedQuantity) {
    this.productId = productId;
    this.province = province;
    this.productName = productName;
    this.selectedQuantity = selectedQuantity;
    setInitMessage();
  }

  private void setInitMessage() {
    StringBuilder sb = new StringBuilder();
    sb.append(productName);
    sb.append("를 ");
    sb.append(selectedQuantity);
    sb.append("만큼 구매합니다.");
    message = sb.toString();
  }


}
