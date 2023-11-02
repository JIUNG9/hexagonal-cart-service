package com.bit.lotte.fresh.cart.service.dto.response;

import com.bit.lotte.fresh.cart.common.domain.valueobject.ProductId;
import com.bit.lotte.fresh.cart.domain.valueobject.Province;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateProductStockResponse {

  private ProductId productId;
  private long updatedProductStockNumber;
  private Province province;
  private String message;

  public UpdateProductStockResponse(
      ProductId productId, long updatedProductStockNumber,
      Province province) {
    this.productId = productId;
    this.updatedProductStockNumber = updatedProductStockNumber;
    this.province = province;
    setInitMessage();
  }

  private void setInitMessage() {
    StringBuilder sb = new StringBuilder();
    sb.append(productId);
    sb.append("의 재고가");
    sb.append(updatedProductStockNumber);
    sb.append("로 업데이트 됩니다.");
    message = sb.toString();
  }
}
